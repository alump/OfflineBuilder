package org.vaadin.alump.offlinebuilder;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.settings.ApplicationCacheSettings;

import javax.servlet.ServletException;

/**
 * Offline enabled servlet
 */
public class OfflineServlet extends TouchKitServlet {

    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        ApplicationCacheSettings cacheSettings = getTouchKitSettings().getApplicationCacheSettings();
        cacheSettings.setCacheManifestEnabled(true);
        //cacheSettings.setOfflineModeEnabled(true);
    }
}
