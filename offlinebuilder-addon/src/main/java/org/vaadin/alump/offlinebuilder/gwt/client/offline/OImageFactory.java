package org.vaadin.alump.offlinebuilder.gwt.client.offline;

import org.vaadin.alump.offlinebuilder.gwt.client.conn.OImageConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineConnector;

/**
 * Created by alump on 07/07/14.
 */
public class OImageFactory extends OfflineFactory {

    public OImageFactory() {
        super();
    }

    @Override
    protected OfflineConnector createConnector() {
        return new OImageConnector();
    }
}