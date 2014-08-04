package org.vaadin.alump.offlinebuilder;

import com.vaadin.annotations.Title;
import com.vaadin.server.AbstractExtension;
import com.vaadin.server.Extension;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.vaadin.alump.offlinebuilder.shared.OfflineUIExtensionState;

/**
 * Extension used
 */
public class OfflineUIExtension extends AbstractExtension {

    private OfflineUIExtension() {

    }

    /**
     * Get current instance of OfflineUIExtension. Instance will be created if not defined yet. Current UI must be
     * available.
     * @return Instance of OfflineUIExtension
     */
    public static OfflineUIExtension get() {
        UI ui = UI.getCurrent();
        if(ui == null) {
            throw new IllegalStateException("Current UI not defined. You most provide it as parameter.");
        }
        return get(ui);
    }

    /**
     * Get current instance of OfflineUIExtension. Instance will be created if not defined yet.
     * @param ui Current UI instance
     * @return Instance of OfflineUIExtension
     */
    public static OfflineUIExtension get(UI ui) {
        for(Extension extension : ui.getExtensions()) {
            if(extension instanceof OfflineUIExtension) {
                return (OfflineUIExtension) extension;
            }
        }
        OfflineUIExtension extension = new OfflineUIExtension();
        extension.extend(ui);

        Title title = ui.getClass().getAnnotation(Title.class);
        if (title != null) {
            extension.setOfflineTitle(title.value());
        }

        return extension;
    }

    protected OfflineUIExtensionState getState() {
        return (OfflineUIExtensionState)super.getState();
    }

    /**
     * Define the root component of offline mode
     * @param component Root component of offline mode. Must be in UI tree of online mode.
     */
    public void setOfflineRoot(Component component) {
        getState().offlineRoot = component;
    }

    /**
     * Get current offline root
     * @return
     */
    public Component getOfflineRoot() {
        return (Component)getState().offlineRoot;
    }

    public void setOfflineTitle(String title) {
        getState().title = title;
    }

    public String getOfflineTitle() {
        return getState().title;
    }
}
