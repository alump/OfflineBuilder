package org.vaadin.alump.offlinebuilder.gwt.client.offline;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.*;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.communication.JsonDecoder;
import com.vaadin.client.communication.JsonEncoder;
import com.vaadin.client.metadata.Type;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.client.ui.AbstractHasComponentsConnector;

import com.google.gwt.storage.client.Storage;
import com.vaadin.shared.communication.SharedState;
import org.vaadin.alump.offlinebuilder.gwt.client.FactoryFromString;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineContainerConnector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by alump on 09/06/14.
 */
public abstract class OfflineFactory {

    public final static String KEY_PREFIX = "o-";
    public final static String KEY_SEPARATOR = "-";

    public final static String FACTORY_KEY = "factory";
    public final static String STATE_KEY = "state";
    public final static String CHILDREN_KEY = "children";

    private String pid;

    private final static Logger logger = Logger.getLogger(OfflineFactory.class.getName());

    public OfflineFactory() {

    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getKey() {
        return KEY_PREFIX + getPid();
    }

    public String getKey(String key) {
        return getKey() + KEY_SEPARATOR + key;
    }

    protected static String resolvePath(AbstractComponentConnector connector) {
        return null;
    }

    public OfflineConnector createInstance(String pid) {
        OfflineConnector connector = createConnector();
        connector.doInit(pid, null);
        return connector;
    }

    protected abstract OfflineConnector createConnector();

    protected void readState(OfflineConnector connector) {
        String jsonState = getItem(STATE_KEY);
        connector.setOffline(true);
        SharedState state = decodeState(jsonState, connector.getState(), connector.getConnection());
        connector.onOfflineState(state);
        String jsonChildren = getItem(CHILDREN_KEY);

        if(jsonChildren != null) {
            JSONValue value = JSONParser.parseStrict(jsonChildren);
            List<String> childrenPids = readStringList(getKey(CHILDREN_KEY));
            List<OfflineConnector> children = new ArrayList<OfflineConnector>();
            List<OfflineFactory> childFactories = new ArrayList<OfflineFactory>();
            for(String pid : childrenPids) {
                OfflineFactory childFactory = getOfflineFactory(pid);
                OfflineConnector childConnector = childFactory.createInstance(pid);
                childFactory.readState(childConnector);
                children.add(childConnector);
                childFactories.add(childFactory);
            }
            if(!children.isEmpty()) {
                ((OfflineContainerConnector)connector).onOfflineHierarchy(children);
            }
            for(int i = 0; i < childFactories.size(); ++i) {
                childFactories.get(i).readState(children.get(i));
            }
        }
    }

    protected void writeState(OfflineConnector connector) {
        Storage storage = Storage.getLocalStorageIfSupported();
        JSONValue json = encodeState(connector.getState(), connector.getConnection());
        setItem(STATE_KEY, json.toString());

        setItem(FACTORY_KEY, this.getClass().getName());

        if(connector instanceof AbstractHasComponentsConnector) {
            AbstractHasComponentsConnector cc = (AbstractHasComponentsConnector)connector;
            List<String> children = new ArrayList<String>();
            for(ComponentConnector c : cc.getChildComponents()) {
                if(c instanceof OfflineConnector) {
                    OfflineConnector oc = (OfflineConnector)c;
                    OfflineFactory of = getOfflineFactory(oc);
                    of.writeState(oc);
                    children.add(oc.getConnectorId());
                }
            }

            writeStringList(getKey(CHILDREN_KEY), children);
        }
    }

    public static void writeStringList(String key, List<String> list) {
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < list.size(); ++i) {
            jsonArray.set(i, new JSONString(list.get(i)));
        }
        Storage.getLocalStorageIfSupported().setItem(key, jsonArray.toString());
    }

    public static List<String> readStringList(String key) {
        List<String> list = new ArrayList<String>();
        JSONValue array = JSONParser.parseStrict(Storage.getLocalStorageIfSupported().getItem(key));
        for(int i = 0; i < array.isArray().size(); ++i) {
            list.add(array.isArray().get(i).isString().stringValue());
        }
        return list;
    }

    public void clearAllItems() {
        Storage storage = Storage.getLocalStorageIfSupported();
        Set<String> removedKeys = new HashSet<String>();
        for(int i = 0; i < storage.getLength(); ++i) {
            if(storage.key(i).startsWith(KEY_PREFIX)) {
                removedKeys.add(storage.key(i));
            }
        }
        for(String key : removedKeys) {
            storage.removeItem(key);
        }
    }

    protected void setItem(String key, String value) {
        Storage.getLocalStorageIfSupported().setItem(getKey(key), value);
    }

    protected void clearItem(String key) {
        setItem(key, null);
    }

    protected String getItem(String key) {
        return Storage.getLocalStorageIfSupported().getItem(getKey(key));
    }

    protected static OfflineConnector getOfflineConnector(String className) {
        FactoryFromString factory = GWT.create(FactoryFromString.class);
        return (OfflineConnector)factory.instantiate(className);
    }

    protected static JSONValue encodeState(SharedState state, ApplicationConnection connection) {
        return JsonEncoder.encode(state, new Type(state.getClass()), connection);
    }

    protected SharedState decodeState(String jsonState, SharedState state, ApplicationConnection connection) {
        JSONValue value = JSONParser.parseStrict(jsonState);
        SharedState ret = (SharedState) JsonDecoder.decodeValue(new Type(state.getClass().getName(), null), value, state, connection);
        return ret;
    }

    public static OfflineFactory getOfflineFactory(String pid) {
        String key = KEY_PREFIX + pid + KEY_SEPARATOR + FACTORY_KEY;
        String factoryName = Storage.getLocalStorageIfSupported().getItem(key);
        FactoryFromString factoryFromString = GWT.create(FactoryFromString.class);
        OfflineFactory factory = (OfflineFactory)factoryFromString.instantiate(factoryName);
        factory.setPid(pid);
        return factory;
    }

    public static OfflineFactory getOfflineFactory(OfflineConnector offlineConnector) {
        FactoryFromString factoryFromString = GWT.create(FactoryFromString.class);
        OfflineFactory factory = (OfflineFactory)factoryFromString.instantiate(offlineConnector.getOfflineFactoryClass().getName());
        factory.setPid(offlineConnector.getConnectorId());
        return factory;
    }
}
