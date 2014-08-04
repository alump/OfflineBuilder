/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client.utils;

import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.HasComponentsConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.shared.Connector;
import org.vaadin.alump.offlinebuilder.client.OfflineConnection;
import org.vaadin.alump.offlinebuilder.client.conn.OfflineConnector;
import org.vaadin.alump.offlinebuilder.client.conn.OfflineContainerConnector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alump on 04/08/14.
 */
public class OfflineUtil {

    public static List<ComponentConnector> handleOfflineConnectorChange(OfflineContainerConnector caller, List<OfflineConnector> children) {
        List<ComponentConnector> componentConnectors = OfflineUtil.getComponentConnectors(children);

        ConnectorHierarchyChangeEvent event = new ConnectorHierarchyChangeEvent();
        //TODO: read old? should be empty now always anyway?
        event.setOldChildren(new ArrayList<ComponentConnector>());
        event.setParent(caller);
        event.setConnector(caller);

        // First set
        caller.setChildComponents(componentConnectors);

        // Then call event
        caller.fireEvent(event);
        //caller.onConnectorHierarchyChange(event);

        return componentConnectors;
    }

    public static boolean isOffline(ComponentConnector connector) {
        //TODO: how slow instanceof is in JS?
        return connector.getConnection() instanceof OfflineConnection;
    }

    public static List<ComponentConnector> getComponentConnectors(List<OfflineConnector> offlineConnectors) {
        List<ComponentConnector> componentConnectors = new ArrayList<ComponentConnector>();
        for(OfflineConnector of : offlineConnectors) {
            componentConnectors.add((ComponentConnector)of);
        }
        return componentConnectors;
    }
}
