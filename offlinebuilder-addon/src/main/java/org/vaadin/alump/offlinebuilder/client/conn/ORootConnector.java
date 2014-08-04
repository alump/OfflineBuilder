package org.vaadin.alump.offlinebuilder.client.conn;

import com.google.gwt.json.client.JSONObject;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.shared.communication.SharedState;
import org.vaadin.alump.offlinebuilder.client.OfflineRootPanel;

/**
 * Created by alump on 09/06/14.
 */
public class ORootConnector implements OfflineConnector {

    protected OfflineRootPanel widget;
    private ApplicationConnection connection;

    public OfflineRootPanel getWidget() {
        if(widget == null) {
            widget = new OfflineRootPanel();
        }

        return widget;
    }

    @Override
    public String getConnectorId() {
        return null;
    }

    @Override
    public void doInit(String connectorId, ApplicationConnection connection) {
        this.connection = connection;
    }

    @Override
    public ApplicationConnection getConnection() {
        return connection;
    }

    @Override
    public SharedState getState() {
        return null;
    }

    @Override
    public void onOfflineState(SharedState state, JSONObject jsonState) {
        // ignore
    }
}
