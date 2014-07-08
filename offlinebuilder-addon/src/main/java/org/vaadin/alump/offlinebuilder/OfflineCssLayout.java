package org.vaadin.alump.offlinebuilder;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OCssLayoutState;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineCssLayout extends CssLayout implements OfflineComponent {

    public OfflineCssLayout() {
        super();
    }

    public OfflineCssLayout(Component... children) {
        super(children);
    }

    public OCssLayoutState getState() {
        return (OCssLayoutState) super.getState();
    }
}
