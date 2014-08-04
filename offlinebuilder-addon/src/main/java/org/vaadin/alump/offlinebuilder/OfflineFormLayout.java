package org.vaadin.alump.offlinebuilder;

import com.vaadin.ui.FormLayout;

/**
 * FormLayout not yet fully supported
 */
@Deprecated
public class OfflineFormLayout extends FormLayout implements OfflineComponent {
    public OfflineFormLayout() {
        super();
    }

    public OfflineFormLayout(com.vaadin.ui.Component... children) {
        super(children);
    }
}
