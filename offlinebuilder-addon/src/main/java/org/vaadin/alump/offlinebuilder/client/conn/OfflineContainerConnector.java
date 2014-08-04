/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client.conn;

import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.HasComponentsConnector;

import java.util.List;

/**
 * Created by alump on 07/07/14.
 */
public interface OfflineContainerConnector extends OfflineConnector, HasComponentsConnector, ConnectorHierarchyChangeEvent.ConnectorHierarchyChangeHandler {

}
