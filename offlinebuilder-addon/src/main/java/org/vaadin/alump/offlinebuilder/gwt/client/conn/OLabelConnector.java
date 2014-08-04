package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.VLabel;
import com.vaadin.client.ui.label.LabelConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.label.LabelState;

import java.util.logging.Logger;

/**
 * Created by alump on 09/06/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineLabel.class, loadStyle = Connect.LoadStyle.EAGER)
public class OLabelConnector extends LabelConnector implements OfflineConnector {

    private LabelState offlineState;
    private boolean offlineMode;
    private final static Logger logger = Logger.getLogger(OLabelConnector.class.getName());

    @Override
    public LabelState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (LabelState) super.getState();
        }
    }

    @Override
    protected Widget createWidget() {
        //TODO: find better way
        return GWT.create(VLabel.class);
    }

    public VLabel getWidget() {
        return super.getWidget();
    }

    @Override
    public void onOfflineState(SharedState state, JSONObject jsonState) {
        offlineState = (LabelState)state;
        fireEvent(new StateChangeEvent(this, jsonState, true));
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);
    }

    @Override
    public void setOffline(boolean offline) {
        offlineMode = offline;
    }
}
