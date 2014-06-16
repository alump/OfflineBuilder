package org.vaadin.alump.offlinebuilder;

import com.vaadin.ui.CssLayout;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OfflineCssLayoutState;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineCssLayout extends CssLayout {

    public OfflineCssLayoutState getState() {
        return (OfflineCssLayoutState) super.getState();
    }
}
