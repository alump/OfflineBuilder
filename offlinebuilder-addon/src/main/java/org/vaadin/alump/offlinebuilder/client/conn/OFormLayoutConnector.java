package org.vaadin.alump.offlinebuilder.client.conn;

import com.google.gwt.json.client.JSONObject;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.formlayout.FormLayoutConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.orderedlayout.AbstractOrderedLayoutState;
import org.vaadin.alump.offlinebuilder.client.utils.OfflineUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Connector for offline form layout
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineFormLayout.class, loadStyle = Connect.LoadStyle.EAGER)
public class OFormLayoutConnector extends FormLayoutConnector implements OfflineContainerConnector {
    private static final Logger logger = Logger.getLogger(OFormLayoutConnector.class.getName());
    private AbstractOrderedLayoutState offlineState;

    public AbstractOrderedLayoutState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (AbstractOrderedLayoutState) super.getState();
        }
    }

    @Override
    public void onOfflineState(SharedState state,  JSONObject jsonState) {
        offlineState = (AbstractOrderedLayoutState)state;
        fireEvent(new StateChangeEvent(this, jsonState, true));
    }
}
