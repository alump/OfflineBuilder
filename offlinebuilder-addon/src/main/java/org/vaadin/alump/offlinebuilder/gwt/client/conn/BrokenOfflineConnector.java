package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.shared.communication.SharedState;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.BrokenOfflineFactory;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.OfflineFactory;

import java.util.List;

/**
 * Created by alump on 09/06/14.
 */
public class BrokenOfflineConnector implements OfflineContainerConnector {

    protected Widget widget;
    protected String failedID;
    protected ApplicationConnection connection;

    public BrokenOfflineConnector() {
        this(null);
    }

    public BrokenOfflineConnector(String failedID) {
        setFailedID(failedID);
    }

    public void setFailedID(String failedID) {
        this.failedID = failedID;
    }

    @Override
    public Class<? extends OfflineFactory> getOfflineFactoryClass() {
        return BrokenOfflineFactory.class;
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

    protected Widget createWidget() {
        HTML label = new HTML();
        label.addStyleName("offline-error");
        label.setText("Failed to offline construct: '" + SafeHtmlUtils.htmlEscape(failedID) + "'");
        return label;
    }

    @Override
    public void onOfflineHierarchy(List<OfflineConnector> children) {
        // ignore
    }
}
