package org.vaadin.alump.offlinebuilder;

import com.vaadin.ui.Label;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OLabelState;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineLabel extends Label implements OfflineComponent {

    public OfflineLabel() {
        super();
    }

    public OfflineLabel(String content) {
        super(content);
    }

    public OLabelState getState() {
        return (OLabelState)super.getState();
    }
}
