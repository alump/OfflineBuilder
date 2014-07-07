package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.image.ImageConnector;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.shared.ui.Connect;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.OImageFactory;
import org.vaadin.alump.offlinebuilder.gwt.client.offline.OfflineFactory;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OImageState;

import java.util.logging.Logger;

/**
 * Created by alump on 07/07/14.
 */
@Connect(org.vaadin.alump.offlinebuilder.OfflineImage.class)
public class OImageConnector extends ImageConnector implements OfflineConnector {

    private OImageState offlineState;
    private boolean offlineMode;
    private final static Logger logger = Logger.getLogger(OImageConnector.class.getName());

    @Override
    public OImageState getState() {
        if(offlineState != null) {
            return offlineState;
        } else {
            return (OImageState) super.getState();
        }
    }

    @Override
    public SharedState createState() {
        if(offlineMode) {
            return new OImageState();
        } else {
            return super.createState();
        }
    }

    @Override
    public Class<? extends OfflineFactory> getOfflineFactoryClass() {
        return OImageFactory.class;
    }

    @Override
    public void onOfflineState(SharedState state) {
        offlineState = (OImageState)state;
        StateChangeEvent event = new StateChangeEvent(this, null, true);
        onStateChanged(event);
    }

    @Override
    public void setOffline(boolean offline) {
        offlineMode = offline;
    }
}
