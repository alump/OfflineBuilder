package org.vaadin.alump.offlinebuilder.gwt.client.offline;

import com.google.gwt.user.client.ui.Widget;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OCssLayoutConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineConnector;

/**
 * Created by alump on 09/06/14.
 */
public class OCssLayoutFactory extends OfflineFactory {

    public OCssLayoutFactory() {
        super();
    }

    @Override
    protected OfflineConnector createConnector() {
        return new OCssLayoutConnector();
    }
}
