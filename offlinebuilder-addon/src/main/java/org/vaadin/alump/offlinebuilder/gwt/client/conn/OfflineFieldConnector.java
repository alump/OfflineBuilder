package org.vaadin.alump.offlinebuilder.gwt.client.conn;

/**
 * Created by alump on 18/07/14.
 */
public interface OfflineFieldConnector {

    /**
     * Key used to store value of this field
     * @return Key where value inside this field is stored in offline mode
     */
    String getOfflineValueKey();
}
