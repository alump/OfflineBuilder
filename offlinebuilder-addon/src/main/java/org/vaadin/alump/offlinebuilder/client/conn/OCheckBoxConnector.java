/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client.conn;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.json.client.JSONObject;
import com.vaadin.addon.touchkit.gwt.client.vcom.CheckBoxConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.client.offline.OfflineStorage;
import org.vaadin.alump.offlinebuilder.shared.OCheckBoxState;

import java.util.logging.Logger;

/**
 * Created by alump on 18/07/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineCheckBox.class, loadStyle = Connect.LoadStyle.EAGER)
public class OCheckBoxConnector extends CheckBoxConnector implements OfflineConnector, OfflineFieldConnector {

    protected OCheckBoxState offlineState;
    private final static Logger logger = Logger.getLogger(OCheckBoxConnector.class.getName());

    public OCheckBoxState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (OCheckBoxState)super.getState();
        }
    }

    @Override
    public void onOfflineState(SharedState state,  JSONObject jsonState) {
        offlineState = (OCheckBoxState)state;
        fireEvent(new StateChangeEvent(this, jsonState, true));
    }

    @Override
    public String getOfflineValueKey() {
        return getState().offlineValueKey;
    }

    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);
    }

    @Override
    public void onFocus(FocusEvent event) {
        if(offlineState == null) {
            super.onFocus(event);
        }
    }

    @Override
    public void onBlur(BlurEvent event) {
        if(offlineState == null) {
            super.onBlur(event);
        }
    }

    @Override
    public void onClick(ClickEvent event) {
        if(offlineState == null) {
            super.onClick(event);
        } else {
            if (!isEnabled()) {
                return;
            }

            if (getState().checked != getWidget().getValue()) {
                getState().checked = getWidget().getValue();
                if(getOfflineValueKey() != null) {
                    OfflineStorage.setData(getOfflineValueKey(), getState().checked);
                }
            }
        }
    }
}
