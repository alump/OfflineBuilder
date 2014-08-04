package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.json.client.JSONObject;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.formlayout.FormLayoutConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.AbstractLayoutState;
import com.vaadin.shared.ui.Connect;
import com.vaadin.shared.ui.orderedlayout.AbstractOrderedLayoutState;
import com.vaadin.shared.ui.orderedlayout.HorizontalLayoutState;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alump on 18/07/14.
 */
@Connect(value = org.vaadin.alump.offlinebuilder.OfflineFormLayout.class, loadStyle = Connect.LoadStyle.EAGER)
public class OFormLayoutConnector extends FormLayoutConnector implements OfflineContainerConnector {
    private static final Logger logger = Logger.getLogger(OFormLayoutConnector.class.getName());
    private AbstractOrderedLayoutState offlineState;
    private List<ComponentConnector> offlineChildren;
    private boolean offlineMode = false;

    public AbstractOrderedLayoutState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (AbstractOrderedLayoutState) super.getState();
        }
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
    public void onOfflineState(SharedState state,  JSONObject jsonState) {
        offlineState = (AbstractOrderedLayoutState)state;
        fireEvent(new StateChangeEvent(this, jsonState, true));
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
