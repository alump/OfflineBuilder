/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client.conn;

import com.google.gwt.json.client.JSONObject;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.orderedlayout.HorizontalLayoutConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.orderedlayout.HorizontalLayoutState;
import org.vaadin.alump.offlinebuilder.client.utils.OfflineUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alump on 09/07/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineHorizontalLayout.class, loadStyle = Connect.LoadStyle.EAGER)
public class OHorizontalLayoutConnector extends HorizontalLayoutConnector implements OfflineContainerConnector {
    private static final Logger logger = Logger.getLogger(OHorizontalLayoutConnector.class.getName());
    private HorizontalLayoutState offlineState;

    public HorizontalLayoutState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (HorizontalLayoutState) super.getState();
        }
    }

    @Override
    public void onOfflineState(SharedState state,  JSONObject jsonState) {
        offlineState = (HorizontalLayoutState)state;
        fireEvent(new StateChangeEvent(this, jsonState, true));
    }
}
