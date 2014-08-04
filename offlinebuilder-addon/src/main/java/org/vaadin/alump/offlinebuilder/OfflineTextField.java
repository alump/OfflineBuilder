package org.vaadin.alump.offlinebuilder;

import org.vaadin.alump.offlinebuilder.shared.OTextFieldState;

/**
 * Class not yet supported
 */
@Deprecated
public class OfflineTextField extends AbstractOfflineField<String> {

    protected OTextFieldState getState() {
        return (OTextFieldState)super.getState();
    }

    @Override
    public Class<? extends String> getType() {
        return String.class;
    }

}
