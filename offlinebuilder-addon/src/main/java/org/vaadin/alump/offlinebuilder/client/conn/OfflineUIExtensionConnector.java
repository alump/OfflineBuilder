/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client.conn;

import com.google.gwt.core.client.GWT;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.client.offline.OfflineFactory;
import org.vaadin.alump.offlinebuilder.shared.OfflineUIExtensionState;

/**
 * Created by alump on 16/06/14.
 */
@Connect(org.vaadin.alump.offlinebuilder.OfflineUIExtension.class)
public class OfflineUIExtensionConnector extends AbstractExtensionConnector {

    protected OfflineFactory offlineFactory;

    @Override
    protected void extend(ServerConnector target) {

    }

    public OfflineUIExtensionState getState() {
        return (OfflineUIExtensionState)super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);

        getOfflineFactory().writeRootState(getState(), getConnection());
    }

    public OfflineFactory getOfflineFactory() {
        if(offlineFactory == null) {
            offlineFactory = GWT.create(OfflineFactory.class);
        }
        return offlineFactory;
    }
}
