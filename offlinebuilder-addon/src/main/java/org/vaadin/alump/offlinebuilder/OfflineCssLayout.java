package org.vaadin.alump.offlinebuilder;

import com.vaadin.ui.CssLayout;
import org.vaadin.alump.offlinebuilder.gwt.client.state.OCssLayoutState;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineCssLayout extends CssLayout {

    public OCssLayoutState getState() {
        return (OCssLayoutState) super.getState();
    }
}
