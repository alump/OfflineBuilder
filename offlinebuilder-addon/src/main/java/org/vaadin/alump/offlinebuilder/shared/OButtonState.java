package org.vaadin.alump.offlinebuilder.shared;

import com.vaadin.shared.ui.button.ButtonState;

/**
 * Created by alump on 09/07/14.
 */
public class OButtonState extends ButtonState {

    /**
     * TODO: This should be replace by offline handler checking (no handlers = disabled)
     */
    public boolean disableInOfflineMode = true;
}
