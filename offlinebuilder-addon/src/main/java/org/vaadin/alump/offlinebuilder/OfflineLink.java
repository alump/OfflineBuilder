package org.vaadin.alump.offlinebuilder;

import com.vaadin.ui.Link;

/**
 * Offline version of Link component
 */
public class OfflineLink extends Link {

    public OfflineLink() {
        super();
    }

    public OfflineLink(java.lang.String caption, com.vaadin.server.Resource resource) {
        super(caption, resource);
    }

    public OfflineLink(java.lang.String caption, com.vaadin.server.Resource resource, java.lang.String targetName,
                       int width, int height, com.vaadin.shared.ui.BorderStyle border) {
        super(caption, resource, targetName, width, height, border);
    }

}
