package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.VCssLayout;
import com.vaadin.client.ui.orderedlayout.VerticalLayoutConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.csslayout.CssLayoutState;
import com.vaadin.shared.ui.orderedlayout.VerticalLayoutState;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alump on 09/07/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineVerticalLayout.class, loadStyle = Connect.LoadStyle.EAGER)
public class OVerticalLayoutConnector extends VerticalLayoutConnector implements OfflineContainerConnector {

    private static final Logger logger = Logger.getLogger(OVerticalLayoutConnector.class.getName());
    private VerticalLayoutState offlineState;
    private List<ComponentConnector> offlineChildren;
    private boolean offlineMode = false;

    public VerticalLayoutState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (VerticalLayoutState) super.getState();
        }
    }

    @Override
    public void onOfflineState(SharedState state, JSONObject jsonState) {
        offlineState = (VerticalLayoutState)state;
        StateChangeEvent event = new StateChangeEvent(this, jsonState, true);
        fireEvent(event);
    }

    @Override
    public void onOfflineHierarchy(List<OfflineConnector> children) {
        List<ComponentConnector> componentConnectors = new ArrayList<ComponentConnector>();
        for(OfflineConnector connector : children) {
            ComponentConnector cc = (ComponentConnector)connector;
            cc.setParent(this);
            componentConnectors.add(cc);
        }

        offlineChildren = componentConnectors;
        ConnectorHierarchyChangeEvent event = new ConnectorHierarchyChangeEvent();
        event.setOldChildren(new ArrayList<ComponentConnector>());
        event.setParent(this);
        event.setConnector(this);
        onConnectorHierarchyChange(event);
    }

    @Override
    public void setOffline(boolean offline) {
        offlineMode = offline;
    }

    @Override
    public List<ComponentConnector> getChildComponents() {
        if(offlineChildren != null) {
            return offlineChildren;
        } else {
            return super.getChildComponents();
        }
    }

}
