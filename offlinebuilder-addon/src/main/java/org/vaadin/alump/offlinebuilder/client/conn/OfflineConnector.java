/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client.conn;

import com.google.gwt.json.client.JSONObject;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.shared.communication.SharedState;

/**
 * Created by alump on 09/06/14.
 */
public interface OfflineConnector {

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
    void onOfflineState(SharedState state, JSONObject jsonState);
}
