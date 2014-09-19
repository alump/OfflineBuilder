/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client.conn;

import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.vaadin.alump.offlinebuilder.OfflineFormReader.class)
public class OFormReaderConnector extends AbstractExtensionConnector {

    @Override
    protected void extend(ServerConnector serverConnector) {
        // ignore
    }
}
