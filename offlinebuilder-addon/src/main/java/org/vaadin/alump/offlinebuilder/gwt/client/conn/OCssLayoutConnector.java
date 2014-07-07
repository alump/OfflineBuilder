package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.FastStringSet;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.VCssLayout;
import com.vaadin.client.ui.VLabel;
import com.vaadin.client.ui.csslayout.CssLayoutConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.OCssLayoutFactory;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.OfflineFactory;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OfflineCssLayoutState;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Connect(org.vaadin.alump.offlinebuilder.OfflineCssLayout.class)
public class OCssLayoutConnector extends CssLayoutConnector implements OfflineConnector {

    private static final Logger logger = Logger.getLogger(OCssLayoutConnector.class.getName());
    private OfflineCssLayoutState offlineState;
    private List<ComponentConnector> offlineChildren;
    private boolean offlineMode = false;

    public OfflineCssLayoutState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (OfflineCssLayoutState) super.getState();
        }
    }

    public SharedState createState() {
        if(offlineMode) {
            return new OfflineCssLayoutState();
        } else {
            return super.createState();
        }
    }

    @Override
    protected Widget createWidget() {
        //TODO: find better way
        return GWT.create(VCssLayout.class);
    }

    @Override
    public void onOfflineState(SharedState state) {
        offlineState = (OfflineCssLayoutState)state;
        StateChangeEvent event = new StateChangeEvent(this, null, true);
        this.onStateChanged(event);
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
    public Class<? extends OfflineFactory> getOfflineFactoryClass() {
        return OCssLayoutFactory.class;
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
