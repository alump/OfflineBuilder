package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.communication.SharedState;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.OfflineFactory;

import java.util.List;

/**
 * Created by alump on 09/06/14.
 */
public interface OfflineConnector {

    Class <? extends OfflineFactory> getOfflineFactoryClass();

    /**
     * Matching with method in connector
     * @return
     */
    String getConnectorId();

    /**
     * Matching with method in Connector
     * @param connectorId
     * @param connection
     */
    void doInit(String connectorId, ApplicationConnection connection);

    /**
     * Matching with method in Connector
     * @return
     */
    ApplicationConnection getConnection();

    /**
     * Matching with method in Connector
     * @return
     */
    SharedState getState();

    /**
     */
    void onOfflineState(SharedState state);

    void setOffline(boolean offline);
}
