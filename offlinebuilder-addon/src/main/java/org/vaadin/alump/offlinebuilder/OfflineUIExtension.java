package org.vaadin.alump.offlinebuilder;

import com.vaadin.annotations.Title;
import com.vaadin.server.AbstractExtension;
import com.vaadin.server.Extension;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import org.vaadin.alump.offlinebuilder.gwt.shared.OfflineUIExtensionState;

/**
 * Created by alump on 16/06/14.
 */
public class OfflineUIExtension extends AbstractExtension {

    private OfflineUIExtension() {

    }

    public static OfflineUIExtension get() {
        UI ui = UI.getCurrent();
        if(ui == null) {
            throw new IllegalStateException("Current UI not defined. You most provide it as parameter.");
        }
        return get(ui);
    }

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

    public void setOfflineRoot(Component component) {
        getState().offlineRoot = component;
    }

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
