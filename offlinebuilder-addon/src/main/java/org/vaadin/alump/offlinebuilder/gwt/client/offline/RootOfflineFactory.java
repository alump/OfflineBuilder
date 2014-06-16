package org.vaadin.alump.offlinebuilder.gwt.client.offline;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.AbstractComponentState;
import com.vaadin.shared.Connector;
import org.vaadin.alump.offlinebuilder.gwt.client.BuilderOfflineMode;
import org.vaadin.alump.offlinebuilder.gwt.client.FactoryFromString;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.ORootConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OfflineUIExtensionState;

/**
 * Created by alump on 09/06/14.
 */
public class RootOfflineFactory extends OfflineFactory {

    protected final static String TITLE_KEY = "title";
    protected final static String ROOT_KEY = "root";

    public RootOfflineFactory() {
        setPid("root");
    }

    //TODO: is this needed?
    public ORootConnector createRoot() {
        ORootConnector connector = createConnector();
        readState(connector);
        return connector;
    }

    @Override
    public ORootConnector createConnector() {
        return new ORootConnector();
    }

    @Override
    protected void readState(OfflineConnector connector) {
        ORootConnector c = (ORootConnector)connector;

        String title = getItem(TITLE_KEY);
        if(title != null) {
            Window.setTitle(title);
        }

        String rootPid = getItem(ROOT_KEY);
        if(rootPid != null) {
            OfflineFactory rootFactory = getOfflineFactory(rootPid);
            OfflineConnector rootConnector = rootFactory.createInstance(rootPid);
            rootFactory.readState(rootConnector);
            c.getWidget().add(BuilderOfflineMode.resolveWidget(rootConnector));
        } else {
            HTML error = new HTML();
            error.setHTML("No root?");
            c.getWidget().add(error);
        }
    }

    public void writeState(OfflineUIExtensionState state) {
        clearAllItems();
        setItem(TITLE_KEY, state.title);
        Connector rootConnector = state.offlineRoot;
        setItem(ROOT_KEY, rootConnector != null ? rootConnector.getConnectorId() : null);
        if(rootConnector != null) {
            OfflineConnector oc = (OfflineConnector)rootConnector;
            OfflineFactory rootFactory = getOfflineFactory(oc);
            rootFactory.writeState(oc);
        }
    }
}
