package org.vaadin.alump.offlinebuilder;

import com.vaadin.server.Resource;
import com.vaadin.ui.Image;

/**
 * Created by alump on 07/07/14.
 */
public class OfflineImage extends Image implements OfflineComponent {

    public OfflineImage() {
        super();
    }

    public OfflineImage(String caption) {
        super(caption);
    }

    public OfflineImage(String caption, Resource source) {
        super(caption, source);
    }
}
