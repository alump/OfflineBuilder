package org.vaadin.alump.offlinebuilder.gwt.client.offline;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.client.ui.ui.UIConnector;
import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.Connector;
import org.vaadin.alump.offlinebuilder.gwt.client.BuilderOfflineMode;
import org.vaadin.alump.offlinebuilder.gwt.client.FactoryFromString;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.ORootConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OfflineUIExtensionState;

/**
 * Created by alump on 09/06/14.
 */
public class RootOfflineFactory extends OfflineFactory {

    protected final static String TITLE_KEY = "title";
    protected final static String PARENT_CLASS_KEY = "parentclass";
    protected final static String UI_CLASS_KEY = "uiclass";
    protected final static String ROOT_KEY = "root";

    public RootOfflineFactory() {
        setPid("root");
    }

    //TODO: is this needed?
    public ORootConnector createRoot() {
        ORootConnector connector = createConnector();
        return connector;
    }

    @Override
    public ORootConnector createConnector() {
        return new ORootConnector();
    }

    @Override
    public void readState(OfflineConnector connector) {
        ORootConnector c = (ORootConnector)connector;

        String title = getItem(TITLE_KEY);
        if(title != null) {
            Window.setTitle(title);
        }

        // Add stylenames that where in online mode v-ui element
        String classesAttribute = getItem(UI_CLASS_KEY);
        if(classesAttribute != null && !classesAttribute.isEmpty()) {
            c.getWidget().addStyleName(classesAttribute);
        }

        String parentClassAttribute = getItem(PARENT_CLASS_KEY);
        if(parentClassAttribute != null && !parentClassAttribute.isEmpty()) {
            c.getWidget().getElement().getParentElement().addClassName(parentClassAttribute);
        }

        String rootPid = getItem(ROOT_KEY);
        if(rootPid != null) {
            OfflineFactory rootFactory = getOfflineFactory(rootPid);
            OfflineConnector rootConnector = rootFactory.createInstance(rootPid);
            c.getWidget().add(BuilderOfflineMode.resolveWidget(rootConnector));
            rootFactory.readState(rootConnector);
        } else {
            HTML error = new HTML();
            error.setHTML("No root?");
            c.getWidget().add(error);
        }
    }

    public void writeState(OfflineUIExtensionState state) {
        clearAllItems();
        setItem(TITLE_KEY, state.title);
        Connector rootConnector = state.offlineRoot;
        setItem(ROOT_KEY, rootConnector != null ? rootConnector.getConnectorId() : null);

        if(rootConnector != null) {
            OfflineConnector oc = (OfflineConnector)rootConnector;
            OfflineFactory rootFactory = getOfflineFactory(oc);
            rootFactory.writeState(oc);
        }
    }

    public void writeState(OfflineUIExtensionState state, ApplicationConnection connection) {
        writeState(state);

        if(connection != null) {
            setItem(UI_CLASS_KEY, connection.getUIConnector().getWidget().getElement().getClassName());
            setItem(PARENT_CLASS_KEY, connection.getUIConnector().getWidget().getElement().getParentElement().getClassName());
        } else {
            setItem(UI_CLASS_KEY, "");
            setItem(PARENT_CLASS_KEY, "");
        }
    }
}
