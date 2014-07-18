package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.json.client.JSONObject;
import com.vaadin.addon.touchkit.gwt.client.vcom.CheckBoxConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.OfflineStorage;
import org.vaadin.alump.offlinebuilder.gwt.shared.OCheckBoxState;

import java.util.logging.Logger;

/**
 * Created by alump on 18/07/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineCheckBox.class, loadStyle = Connect.LoadStyle.EAGER)
public class OCheckBoxConnector extends CheckBoxConnector implements OfflineConnector, OfflineFieldConnector {

    private boolean offlineMode;
    protected OCheckBoxState offlineState;
    private final static Logger logger = Logger.getLogger(OCheckBoxConnector.class.getName());

    public OCheckBoxState getState() {
        if(offlineMode) {
            return offlineState;
        } else {
            return (OCheckBoxState)super.getState();
        }
    }

    @Override
    public void onOfflineState(SharedState state,  JSONObject jsonState) {
        offlineState = (OCheckBoxState)state;
        StateChangeEvent event = new StateChangeEvent(this, jsonState, true);
        onStateChanged(event);
    }

    @Override
    public void setOffline(boolean offline) {
        offlineMode = offline;
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
        if(!offlineMode) {
            super.onFocus(event);
        }
    }

    @Override
    public void onBlur(BlurEvent event) {
        if(!offlineMode) {
            super.onBlur(event);
        }
    }

    @Override
    public void onClick(ClickEvent event) {
        if(!offlineMode) {
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
