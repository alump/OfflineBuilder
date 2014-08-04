/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder;

/**
 * Interface all offline field components must implement
 */
public interface OfflineField extends OfflineComponent {

    public void setOfflineValueKey(String key);

    public String getOfflineValueKey();
}
