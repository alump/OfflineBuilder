package org.vaadin.alump.offlinebuilder;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OButtonState;

/**
 * Broken (serilization issue with numbers in state)
 */
@Deprecated
public class OfflineButton extends Button implements OfflineComponent {

    public OfflineButton() {
        super();
    }

    public OfflineButton(String caption) {
        super(caption);
    }

    public OfflineButton(Resource icon) {
        super(icon);
    }

    public OfflineButton(String caption, Resource icon) {
        super(caption, icon);
    }

    public OfflineButton(String caption, ClickListener listener) {
        super(caption, listener);
    }

    protected OButtonState getState() {
        return (OButtonState)super.getState();
    }
}
