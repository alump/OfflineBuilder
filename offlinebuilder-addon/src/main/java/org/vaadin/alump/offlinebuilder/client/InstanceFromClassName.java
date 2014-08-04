/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client;

/**
 * Interface to own constructor. TODO: maybe replace with vaadin's own magic
 */
public interface InstanceFromClassName {
    public Object instantiate(String klassName);
}
