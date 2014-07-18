package org.vaadin.alump.offlinebuilder;

import org.vaadin.alump.offlinebuilder.gwt.shared.OTextFieldState;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineTextField extends AbstractOfflineField<String> {

    protected OTextFieldState getState() {
        return (OTextFieldState)super.getState();
    }

    @Override
    public Class<? extends String> getType() {
        return String.class;
    }

}
