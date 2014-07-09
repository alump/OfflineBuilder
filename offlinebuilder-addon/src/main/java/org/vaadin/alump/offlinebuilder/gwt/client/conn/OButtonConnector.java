package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.button.ButtonConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.gwt.shared.OButtonState;

import java.util.logging.Logger;

/**
 * Created by alump on 08/07/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineButton.class, loadStyle = Connect.LoadStyle.EAGER)
public class OButtonConnector extends ButtonConnector implements OfflineConnector {

    private OButtonState offlineState;
    private boolean offlineMode = false;
    private final static Logger logger = Logger.getLogger(OButtonConnector.class.getName());

    public OButtonState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (OButtonState)super.getState();
        }
    }

    @Override
    public void onOfflineState(SharedState state) {
        offlineState = (OButtonState)state;

        // TODO: replace with offline handler checks
        if(offlineState.disableInOfflineMode) {
            offlineState.enabled = false;
        }

        StateChangeEvent event = new StateChangeEvent(this, null, true);
        onStateChanged(event);
    }

    @Override
    public void setOffline(boolean offline) {
        offlineMode = offline;
    }

    @Override
    public void onBlur(BlurEvent event) {
        if(offlineMode) {

        } else {
            super.onBlur(event);
        }
    }

    @Override
    public void onClick(ClickEvent event) {
        if(offlineMode) {

        } else {
            super.onClick(event);
        }
    }
}
