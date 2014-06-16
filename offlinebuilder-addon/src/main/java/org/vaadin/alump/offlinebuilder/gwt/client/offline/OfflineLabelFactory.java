package org.vaadin.alump.offlinebuilder.gwt.client.offline;

import org.vaadin.alump.offlinebuilder.gwt.client.conn.OLabelConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineConnector;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineLabelFactory extends OfflineFactory {

    public OfflineLabelFactory() {
        super();
    }

    @Override
    protected OfflineConnector createConnector() {
        return new OLabelConnector();
    }
}
