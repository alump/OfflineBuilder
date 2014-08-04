package org.vaadin.alump.offlinebuilder.client.conn;

import com.google.gwt.json.client.JSONObject;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.link.LinkConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.shared.OLinkState;

/**
 * Created by alump on 04/08/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineLink.class, loadStyle = Connect.LoadStyle.EAGER)
public class OLinkConnector extends LinkConnector implements OfflineConnector {

    private OLinkState offlineState;

    public OLinkState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (OLinkState) super.getState();
        }
    }

    @Override
    public void onOfflineState(SharedState state, JSONObject jsonState) {
        offlineState = (OLinkState)state;
        fireEvent(new StateChangeEvent(this, jsonState, true));
    }
}
