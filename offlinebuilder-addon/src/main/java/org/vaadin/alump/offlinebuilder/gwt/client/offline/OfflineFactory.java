package org.vaadin.alump.offlinebuilder.gwt.client.offline;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.communication.JsonDecoder;
//import com.vaadin.client.communication.JsonEncoder;
import com.vaadin.client.metadata.Type;
import com.vaadin.client.ui.AbstractHasComponentsConnector;

import com.vaadin.shared.Connector;
import com.vaadin.shared.communication.SharedState;
import org.vaadin.alump.offlinebuilder.gwt.client.BuilderOfflineMode;
import org.vaadin.alump.offlinebuilder.gwt.client.InstanceFromClassName;
import org.vaadin.alump.offlinebuilder.gwt.client.OfflineConnection;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.ORootConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineContainerConnector;
import org.vaadin.alump.offlinebuilder.gwt.shared.OfflineUIExtensionState;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineFactory {

    private final static Logger logger = Logger.getLogger(OfflineFactory.class.getName());

    protected final static String TITLE_KEY = "title";
    protected final static String PARENT_CLASS_KEY = "parentclass";
    protected final static String UI_CLASS_KEY = "uiclass";
    protected final static String ROOT_KEY = "root";

    protected static OfflineConnection offlineConnection;

    public OfflineFactory() {
    }

    public static OfflineConnection getOfflineApplicationConnection() {
        if(offlineConnection == null) {
            offlineConnection = new OfflineConnection();
        }
        return offlineConnection;
    }

    protected void readState(OfflineConnector connector) {
        JSONValue jsonState = OfflineStorage.getStateJson(connector.getConnectorId());
        connector.setOffline(true);
        SharedState state = decodeState(jsonState, connector.getState(), connector.getConnection());
        if(state != null) {
            connector.onOfflineState(state, jsonState.isObject());
            List<String> children = OfflineStorage.getChildren(connector.getConnectorId());

            if (children != null) {
                List<OfflineConnector> added = new ArrayList<OfflineConnector>();
                for (String pid : children) {
                    OfflineConnector childConnector = getOfflineConnector(pid);
                    added.add(childConnector);
                }
                if (!added.isEmpty()) {
                    ((OfflineContainerConnector) connector).onOfflineHierarchy(added);
                }
                for(OfflineConnector child : added) {
                    readState(child);
                }
            }
        } else {
            logger.severe("Failed to read state of component " + connector.getConnectorId() + ", leaving it uninitialized.");
        }
    }

    protected void writeState(OfflineConnector connector) {
        JSONValue json = encodeState(connector.getState(), connector.getConnection());
        OfflineStorage.setStateJson(connector.getConnectorId(), json);
        OfflineStorage.setConnectorClass(connector.getConnectorId(), connector.getClass().getName());

        if(connector instanceof AbstractHasComponentsConnector) {
            AbstractHasComponentsConnector cc = (AbstractHasComponentsConnector)connector;
            List<String> children = new ArrayList<String>();
            for(ComponentConnector c : cc.getChildComponents()) {
                if(c instanceof OfflineConnector) {
                    OfflineConnector oc = (OfflineConnector)c;
                    writeState(oc);
                    children.add(oc.getConnectorId());
                }
            }

            OfflineStorage.setChildren(connector.getConnectorId(), children);
        }
    }

    protected static JSONValue encodeState(SharedState state, ApplicationConnection connection) {
        return JsonEncoder.encode(state, new Type(state.getClass()), connection);
    }

    protected SharedState decodeState(JSONValue jsonState, SharedState state, ApplicationConnection connection) {
        SharedState ret;

        try {
            ret = (SharedState) JsonDecoder.decodeValue(new Type(state.getClass().getName(), null), jsonState, state, connection);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "JSON State decoding exception", e);
            logger.warning("Failed to decode state of component with JSON: " + jsonState.toString());
            ret = null;
        }
        return ret;
    }

    public static OfflineConnector getOfflineConnector(String pid) {
        String connectorClass = OfflineStorage.getConnectorClass(pid);
        InstanceFromClassName generator = GWT.create(InstanceFromClassName.class);
        OfflineConnector connector = (OfflineConnector)generator.instantiate(connectorClass);

        connector.setOffline(true);
        connector.doInit(pid, getOfflineApplicationConnection());
        return connector;
    }

    public static String generateRootKey(String key) {
        return ROOT_KEY + OfflineStorage.KEY_SEPARATOR + key;
    }

    public void readRootState(ORootConnector connector) {
        String title = OfflineStorage.get(generateRootKey(TITLE_KEY));
        if(title != null) {
            Window.setTitle(title);
        }

        // Add stylenames that where in online mode v-ui element
        String classesAttribute = OfflineStorage.get(generateRootKey(UI_CLASS_KEY));
        if(classesAttribute != null && !classesAttribute.isEmpty()) {
            connector.getWidget().addStyleName(classesAttribute);
        }

        String parentClassAttribute = OfflineStorage.get(generateRootKey(PARENT_CLASS_KEY));
        if(parentClassAttribute != null && !parentClassAttribute.isEmpty()) {
            connector.getWidget().getElement().getParentElement().addClassName(parentClassAttribute);
        }

        String rootPid = OfflineStorage.get(generateRootKey(ROOT_KEY));
        if(rootPid != null) {
            OfflineConnector rootConnector = getOfflineConnector(rootPid);
            connector.getWidget().add(BuilderOfflineMode.resolveWidget(rootConnector));
            readState(rootConnector);
        } else {
            HTML error = new HTML();
            error.setHTML("No root?");
            connector.getWidget().add(error);
        }
    }

    public void writeRootState(OfflineUIExtensionState state, ApplicationConnection connection) {
        OfflineStorage.clearAllItems();
        OfflineStorage.set(generateRootKey(TITLE_KEY), state.title);
        Connector rootConnector = state.offlineRoot;
        OfflineStorage.set(generateRootKey(ROOT_KEY), rootConnector != null ? rootConnector.getConnectorId() : null);

        if(rootConnector != null) {
            writeState((OfflineConnector)rootConnector);
        }

        if(connection != null) {
            OfflineStorage.set(generateRootKey(UI_CLASS_KEY),
                    connection.getUIConnector().getWidget().getElement().getClassName());
            OfflineStorage.set(generateRootKey(PARENT_CLASS_KEY),
                    connection.getUIConnector().getWidget().getElement().getParentElement().getClassName());
        } else {
            OfflineStorage.set(generateRootKey(UI_CLASS_KEY), "");
            OfflineStorage.set(generateRootKey(PARENT_CLASS_KEY), "");
        }
    }
}
