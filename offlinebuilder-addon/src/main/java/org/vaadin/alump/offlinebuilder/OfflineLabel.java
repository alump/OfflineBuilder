package org.vaadin.alump.offlinebuilder;

import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
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

    public OfflineLabel(Property contentSource) {
        super(contentSource);
    }

    public OfflineLabel(String content, ContentMode contentMode) {
        super(content, contentMode);
    }

    public OfflineLabel(Property contentSource, ContentMode contentMode) {
        super(contentSource, contentMode);
    }

    public OLabelState getState() {
        return (OLabelState)super.getState();
    }
}
