package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.shared.communication.SharedState;
import org.vaadin.alump.offlinebuilder.gwt.client.OfflineRootPanel;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.OfflineFactory;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.RootOfflineFactory;

import java.util.List;

/**
 * Created by alump on 09/06/14.
 */
public class ORootConnector implements OfflineConnector {

    protected OfflineRootPanel widget;
    private ApplicationConnection connection;

    @Override
    public Class<? extends OfflineFactory> getOfflineFactoryClass() {
        return RootOfflineFactory.class;
    }

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
    public void onOfflineState(SharedState state) {
        // ignore
    }

    @Override
    public void setOffline(boolean offline) {
        // ignore
    }
}
