package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.OfflineUIExtension;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.RootOfflineFactory;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OfflineUIExtensionState;

/**
 * Created by alump on 16/06/14.
 */
@Connect(org.vaadin.alump.offlinebuilder.OfflineUIExtension.class)
public class OfflineUIExtensionConnector extends AbstractExtensionConnector {

    private RootOfflineFactory rootFactory;

    @Override
    protected void extend(ServerConnector target) {

    }

    public OfflineUIExtensionState getState() {
        return (OfflineUIExtensionState)super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);

        getRootFactory().writeState(getState(), getConnection());
    }

    public RootOfflineFactory getRootFactory() {
        if(rootFactory == null) {
            rootFactory = new RootOfflineFactory();
        }
        return rootFactory;
    }
}
