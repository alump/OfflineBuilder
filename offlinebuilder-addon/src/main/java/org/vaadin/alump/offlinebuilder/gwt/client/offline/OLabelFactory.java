package org.vaadin.alump.offlinebuilder.gwt.client.offline;

import org.vaadin.alump.offlinebuilder.gwt.client.conn.OLabelConnector;
import org.vaadin.alump.offlinebuilder.gwt.client.conn.OfflineConnector;

/**
 * Created by alump on 07/07/14.
 */
public class OLabelFactory  extends OfflineFactory {

    public OLabelFactory() {
        super();
    }

    @Override
    protected OfflineConnector createConnector() {
        return new OLabelConnector();
    }
}