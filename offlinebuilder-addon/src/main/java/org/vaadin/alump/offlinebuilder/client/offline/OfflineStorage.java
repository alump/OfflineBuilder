/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client.offline;

import com.google.gwt.json.client.*;
import com.google.gwt.storage.client.Storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by alump on 08/07/14.
 */
public class OfflineStorage {

    private final static Logger logger = Logger.getLogger(OfflineStorage.class.getName());

    public final static String KEY_PREFIX = "o-";
    public final static String KEY_SEPARATOR = "-";

    public final static String CONNECTOR_KEY = "connector";
    public final static String STATE_KEY = "state";
    public final static String CHILDREN_KEY = "children";
    public final static String DATA_KEY = KEY_PREFIX + "data";

    private final static Storage storage = Storage.getLocalStorageIfSupported();

    protected static String generateKey(String pid) {
        return KEY_PREFIX + pid;
    }

    public static String generateKey(String pid, String key) {
        return generateKey(pid) + KEY_SEPARATOR + key;
    }

    protected static String get(String key) {
        return storage.getItem(key);
    }

    public static String get(String pid, String key) {
        return get(generateKey(pid, key));
    }

    public static String getConnectorClass(String pid) {
        return get(pid, CONNECTOR_KEY);
    }

    public static void setConnectorClass(String pid, String className) {
        set(pid, CONNECTOR_KEY, className);
    }

    public static JSONValue getStateJson(String pid) {
        return JSONParser.parseStrict(get(pid, STATE_KEY));
    }

    protected static void set(String key, String value) {
        storage.setItem(key, value);
    }

    public static void set(String pid, String key, String value) {
        set(generateKey(pid, key), value);
    }

    public static void setData(String key, String value) {
        writeData(key, value);
    }

    public static void setData(String key, Boolean value) {
        writeData(key, value);
    }

    public static void setData(String key, Number value) {
        writeData(key, value);
    }

    protected static JSONObject cachedData;

    protected static JSONObject getData() {
        if(cachedData == null) {
            String jsonString = get(DATA_KEY);
            JSONObject data;
            if(jsonString == null) {
                data = new JSONObject();
            } else {
                data = JSONParser.parseStrict(jsonString).isObject();
                if(data == null) {
                    logger.severe("Invalid data content read from Local Storage");
                    data = new JSONObject();
                }
            }
            cachedData = data;
        }

        return cachedData;
    }

    protected static void storeData() {
        set(DATA_KEY, getData().toString());
    }

    protected static void writeData(String key, Object value) {
        JSONObject data = getData();
        JSONValue jsonValue = null;
        if(value instanceof String) {
            jsonValue = new JSONString((String) value);
        } else if (value instanceof Boolean) {
            jsonValue = JSONBoolean.getInstance((Boolean)value);
        } else if (value instanceof Number) {
            jsonValue = new JSONNumber(((Number) value).doubleValue());
        }

        if(jsonValue == null) {
            logger.severe("Can not store given '" + key + "' value's type to data");
            return;
        }
        data.put(key, jsonValue);
        storeData();
    }

    public static void setStateJson(String pid, JSONValue json) {
        set(pid, STATE_KEY, json != null ? json.toString() : null);
    }

    protected static void writeStringList(String key, List<String> list) {
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < list.size(); ++i) {
            jsonArray.set(i, new JSONString(list.get(i)));
        }
        Storage.getLocalStorageIfSupported().setItem(key, jsonArray.toString());
    }

    public static void setStringList(String pid, String key, List<String> list) {
        writeStringList(generateKey(pid, key), list);
    }

    public static void setChildren(String pid, List<String> children) {
        setStringList(pid, CHILDREN_KEY, children);
    }

    protected static List<String> getStringList(String key) {
        String value = storage.getItem(key);
        if(value == null) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        JSONValue array = JSONParser.parseStrict(value);
        for(int i = 0; i < array.isArray().size(); ++i) {
            list.add(array.isArray().get(i).isString().stringValue());
        }
        return list;
    }

    public static List<String> getStringList(String pid, String key) {
        return getStringList(generateKey(pid, key));
    }

    public static List<String> getChildren(String pid) {
        return getStringList(pid, CHILDREN_KEY);
    }

    public static void clearAllItems() {
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
}
