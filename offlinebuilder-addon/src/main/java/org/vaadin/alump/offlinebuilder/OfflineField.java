package org.vaadin.alump.offlinebuilder;

/**
 * Interface all offline field components must implement
 */
public interface OfflineField extends OfflineComponent {

    public void setOfflineValueKey(String key);

    public String getOfflineValueKey();
}
