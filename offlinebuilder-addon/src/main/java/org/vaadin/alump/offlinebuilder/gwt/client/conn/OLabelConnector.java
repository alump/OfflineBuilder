package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.VLabel;
import com.vaadin.client.ui.label.LabelConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OLabelState;

import java.util.logging.Logger;

/**
 * Created by alump on 09/06/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineLabel.class, loadStyle = Connect.LoadStyle.EAGER)
public class OLabelConnector extends LabelConnector implements OfflineConnector {

    private OLabelState offlineState;
    private boolean offlineMode;
    private final static Logger logger = Logger.getLogger(OLabelConnector.class.getName());

    @Override
    public OLabelState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (OLabelState) super.getState();
        }
    }

    @Override
    public SharedState createState() {
        if(offlineMode) {
            return new OLabelState();
        } else {
            return super.createState();
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
    public void onOfflineState(SharedState state) {
        offlineState = (OLabelState)state;
        StateChangeEvent event = new StateChangeEvent(this, null, true);
        onStateChanged(event);
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
