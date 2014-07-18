package org.vaadin.alump.offlinebuilder;

import com.vaadin.ui.FormLayout;

/**
 * Created by alump on 18/07/14.
 */
public class OfflineFormLayout extends FormLayout implements OfflineComponent {
    public OfflineFormLayout() {
        super();
    }

    public OfflineFormLayout(com.vaadin.ui.Component... children) {
        super(children);
    }
}
