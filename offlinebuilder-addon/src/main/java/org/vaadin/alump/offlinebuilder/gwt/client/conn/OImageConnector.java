package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.json.client.JSONObject;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.image.ImageConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.image.ImageState;

import java.util.logging.Logger;

/**
 * Created by alump on 07/07/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineImage.class, loadStyle = Connect.LoadStyle.EAGER)
public class OImageConnector extends ImageConnector implements OfflineConnector {

    private ImageState offlineState;
    private boolean offlineMode;
    private final static Logger logger = Logger.getLogger(OImageConnector.class.getName());

    @Override
    public ImageState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return super.getState();
        }
    }

    @Override
    public void onOfflineState(SharedState state, JSONObject jsonState) {
        offlineState = (ImageState)state;
        StateChangeEvent event = new StateChangeEvent(this, jsonState, true);
        onStateChanged(event);
    }

    @Override
    public void setOffline(boolean offline) {
        offlineMode = offline;
    }
}
