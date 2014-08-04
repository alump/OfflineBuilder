/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import org.vaadin.alump.offlinebuilder.shared.OButtonState;

/**
 * Offline version of Button
 */
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
