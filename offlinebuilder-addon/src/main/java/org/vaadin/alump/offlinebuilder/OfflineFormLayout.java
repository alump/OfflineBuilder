/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

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
