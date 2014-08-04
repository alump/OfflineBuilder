package org.vaadin.alump.offlinebuilder.client;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.VTooltip;

import java.util.logging.Logger;

/**
 * Few changes required to VTooltip
 */
public class OTooltip extends VTooltip {

    private final static Logger logger = Logger.getLogger(OTooltip.class.getName());

    boolean offlineMode = false;

    public void setOfflineMode(boolean offline) {
        offlineMode = offline;
    }

    @Override
    public void connectHandlersToWidget(Widget widget) {
        //TODO: find workaround of do special implementation for offline mode
        if(!offlineMode) {
            super.connectHandlersToWidget(widget);
        }
    }
}
