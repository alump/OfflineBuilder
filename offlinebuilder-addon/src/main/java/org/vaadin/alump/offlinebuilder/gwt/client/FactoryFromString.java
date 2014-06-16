package org.vaadin.alump.offlinebuilder.gwt.client;

import org.vaadin.alump.offlinebuilder.gwt.FactoryReflectionGenerator;

public interface FactoryFromString {
    public Object instantiate(String klassName);
}
