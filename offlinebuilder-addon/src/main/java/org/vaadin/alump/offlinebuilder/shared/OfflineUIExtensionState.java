/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.shared;

import com.vaadin.shared.Connector;
import com.vaadin.shared.communication.SharedState;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineUIExtensionState extends SharedState {

    public Connector offlineRoot;

    public String title;
}
