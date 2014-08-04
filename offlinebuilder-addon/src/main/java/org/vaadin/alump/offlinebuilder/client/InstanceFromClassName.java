package org.vaadin.alump.offlinebuilder.client;

/**
 * Interface to own constructor. TODO: maybe replace with vaadin's own magic
 */
public interface InstanceFromClassName {
    public Object instantiate(String klassName);
}
