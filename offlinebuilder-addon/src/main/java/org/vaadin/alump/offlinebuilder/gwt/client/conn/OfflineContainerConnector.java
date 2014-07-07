package org.vaadin.alump.offlinebuilder.gwt.client.conn;

import java.util.List;

/**
 * Created by alump on 07/07/14.
 */
public interface OfflineContainerConnector extends OfflineConnector {

    void onOfflineHierarchy(List<OfflineConnector> children);
}
