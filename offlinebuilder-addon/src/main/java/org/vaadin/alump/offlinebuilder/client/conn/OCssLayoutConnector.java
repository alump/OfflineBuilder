package org.vaadin.alump.offlinebuilder.client.conn;

import com.google.gwt.json.client.JSONObject;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.csslayout.CssLayoutConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.csslayout.CssLayoutState;
import org.vaadin.alump.offlinebuilder.client.utils.OfflineUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Connect(value = org.vaadin.alump.offlinebuilder.OfflineCssLayout.class, loadStyle = Connect.LoadStyle.EAGER)
public class OCssLayoutConnector extends CssLayoutConnector implements OfflineContainerConnector {

    private static final Logger logger = Logger.getLogger(OCssLayoutConnector.class.getName());
    private CssLayoutState offlineState;

    public CssLayoutState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (CssLayoutState) super.getState();
        }
    }

    @Override
    public void onOfflineState(SharedState state, JSONObject jsonState) {
        offlineState = (CssLayoutState)state;
        fireEvent(new StateChangeEvent(this, jsonState, true));
    }
}
