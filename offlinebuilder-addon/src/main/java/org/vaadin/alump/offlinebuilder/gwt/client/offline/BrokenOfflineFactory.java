package org.vaadin.alump.offlinebuilder.gwt.client.offline;

import org.vaadin.alump.offlinebuilder.gwt.client.conn.BrokenOfflineConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineConnector;

/**
 * Created by alump on 09/06/14.
 */
public class BrokenOfflineFactory extends OfflineFactory {

    public BrokenOfflineFactory() {
        super();
    }

    @Override
    protected OfflineConnector createConnector() {
        return new BrokenOfflineConnector();
    }
}
