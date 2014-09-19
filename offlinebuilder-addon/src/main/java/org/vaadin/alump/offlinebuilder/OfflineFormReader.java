/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder;

import com.vaadin.server.AbstractExtension;
import com.vaadin.server.Extension;
import com.vaadin.ui.UI;
import org.vaadin.alump.offlinebuilder.shared.OFormReaderClientRpc;
import org.vaadin.alump.offlinebuilder.shared.OFormReaderServerRpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Extension that can be used to read data entered in offine mode
 */
public class OfflineFormReader extends AbstractExtension {

    protected Map<String,List<DataReadListener>> listeners = new HashMap<String, List<DataReadListener>>();

    /**
     * Event thrown when form data requested has been read
     */
    public static class OfflineFormDataEvent {
        private String formId;
        private Map<String,String> data = new HashMap<String,String>();

        /**
         * Get ID of form
         * @return ID of form
         */
        public String getFormId() {
            return formId;
        }

        /**
         * Get value of field
         * @param fieldId Field ID
         * @return Value of field
         */
        public String getValue(String fieldId) {
            return data.get(fieldId);
        }
    }

    public interface DataReadListener {
        void onDataRead(OfflineFormDataEvent event);
    }

    private final OFormReaderServerRpc serverRpc = new OFormReaderServerRpc() {

        @Override
        public void onDataRead(String formId, Map<String, String> data) {
            if(listeners.containsKey(formId)) {
                OfflineFormDataEvent event = new OfflineFormDataEvent();
                event.data = data;
                event.formId = formId;

                for (DataReadListener listener : listeners.get(formId)) {
                    listener.onDataRead(event);
                }

                listeners.remove(formId);
            }
        }
    };

    protected OfflineFormReader() {
        registerRpc(serverRpc, OFormReaderServerRpc.class);
    }

    protected static OfflineFormReader getInstance() {
        return getInstance(UI.getCurrent());
    }

    protected static OfflineFormReader getInstance(UI ui) {
        for(Extension extension : ui.getExtensions()) {
            if(extension instanceof OfflineFormReader) {
                return (OfflineFormReader)extension;
            }
        }

        OfflineFormReader extension = new OfflineFormReader();
        extension.extend(ui);
        return extension;
    }

    /**
     * Read form data from client
     * @param formId ID of form you want to read
     * @param listener Callback instance
     */
    public static void readFormData(String formId, DataReadListener listener) {
        getInstance().read(formId, listener);
    }

    /**
     * Read form data from client
     * @param ui Your application's UI
     * @param formId ID of form you want to read
     * @param listener Callback instance
     */
    public static void readFormData(UI ui, String formId, DataReadListener listener) {
        getInstance(ui).read(formId, listener);
    }

    protected void read(String formId, DataReadListener listener) {
        if(listeners.containsKey(formId)) {
            listeners.get(formId).add(listener);
        } else {
            List<DataReadListener> formListeners = new ArrayList<DataReadListener>();
            formListeners.add(listener);
            listeners.put(formId, formListeners);

            getRpcProxy(OFormReaderClientRpc.class).readFormData(formId);
        }
    }
}
