package org.vaadin.alump.offlinebuilder;

import com.vaadin.data.Property;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import org.vaadin.alump.offlinebuilder.gwt.shared.OCheckBoxState;

/**
 * Created by alump on 18/07/14.
 */
public class OfflineCheckBox extends CheckBox implements OfflineField {

    public OfflineCheckBox() {
        super();
    }

    public OfflineCheckBox(String caption) {
        super(caption);
    }

    public OfflineCheckBox(String caption, boolean initialState) {
        super(caption, initialState);
    }

    public OfflineCheckBox(String caption, Property<?> dataSource) {
        super(caption, dataSource);
    }

    protected OCheckBoxState getState() {
        return (OCheckBoxState) super.getState();
    }

    @Override
    public void setOfflineValueKey(String key) {
        getState().offlineValueKey = key;
    }

    @Override
    public String getOfflineValueKey() {
        return getState().offlineValueKey;
    }
}
