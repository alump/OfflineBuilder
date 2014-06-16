package org.vaadin.alump.offlinebuilder.gwt.client;

import com.vaadin.client.ApplicationConnection;
import com.vaadin.shared.communication.MethodInvocation;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineConnection extends ApplicationConnection {

    @Override
    public void addMethodInvocationToQueue(MethodInvocation invocation,
                                           boolean delayed, boolean lastOnly) {

        // do nothing for now
    }
}
