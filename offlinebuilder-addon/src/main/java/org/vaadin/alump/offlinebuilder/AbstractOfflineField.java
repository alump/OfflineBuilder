package org.vaadin.alump.offlinebuilder;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;
import org.vaadin.alump.offlinebuilder.gwt.shared.AbstractOfflineFieldState;

/**
 * Base class for offline field components that can not be inherited from core components
 */
public abstract class AbstractOfflineField<T> extends AbstractField<T> implements OfflineField {

    protected AbstractOfflineFieldState getState() {
        return (AbstractOfflineFieldState)super.getState();
    }

    public void setOfflineValueKey(String key) {
        getState().offlineValueKey = key;
    }

    public String getOfflineValueKey() {
        return getState().offlineValueKey;
    }
}
