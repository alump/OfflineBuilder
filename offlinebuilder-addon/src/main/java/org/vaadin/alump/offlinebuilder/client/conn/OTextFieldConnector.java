/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client.conn;

import com.google.gwt.json.client.JSONObject;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractFieldConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.client.OTextField;
import org.vaadin.alump.offlinebuilder.shared.OTextFieldState;

import java.util.logging.Logger;

/**
 * Created by alump on 18/07/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineTextField.class, loadStyle = Connect.LoadStyle.EAGER)
public class OTextFieldConnector extends AbstractFieldConnector implements OfflineConnector, OfflineFieldConnector {

    private boolean offlineMode;
    private final static Logger logger = Logger.getLogger(OLabelConnector.class.getName());

    public OTextFieldState getState() {
        return (OTextFieldState)super.getState();
    }

    @Override
    public void onOfflineState(SharedState state, JSONObject jsonState) {

    }

    public void onStateChanged(StateChangeEvent event) {

    }

    public OTextField getWidget() {
        return (OTextField)super.getWidget();
    }

    @Override
    public String getOfflineValueKey() {
        return getState().offlineValueKey;
    }
}
