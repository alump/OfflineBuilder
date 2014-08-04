package org.vaadin.alump.offlinebuilder.client;

import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.ConnectorMap;
import com.vaadin.client.ServerConnector;
import com.vaadin.shared.communication.MethodInvocation;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by alump on 09/06/14.
 */
public class OfflineConnection extends ApplicationConnection {

    private static final Logger logger = Logger.getLogger(OfflineConnection.class.getName());

    private static boolean isOffline = false;

    public static void markOffline() {
        isOffline = true;
    }

    public static boolean isOffline() {
        return isOffline;
    }

    @Override
    public void addMethodInvocationToQueue(MethodInvocation invocation,
                                           boolean delayed, boolean lastOnly) {

        // do nothing for now

    }

    public void updateVariable(String paintableId, String variableName,
                               ServerConnector newValue, boolean immediate) {
        logger.warning("Update '" + variableName + "' variable not supported in offline mode");
    }

    public void updateVariable(String paintableId, String variableName,
                               String newValue, boolean immediate) {
        logger.warning("Update '\" + variableName + \"' variable not supported in offline mode");
    }

    public void updateVariable(String paintableId, String variableName,
                               int newValue, boolean immediate) {
        logger.warning("Update '\" + variableName + \"' variable not supported in offline mode");
    }

    public void updateVariable(String paintableId, String variableName,
                               long newValue, boolean immediate) {
        logger.warning("Update '\" + variableName + \"' variable not supported in offline mode");
    }

    public void updateVariable(String paintableId, String variableName,
                               float newValue, boolean immediate) {
        logger.warning("Update '\" + variableName + \"' variable not supported in offline mode");
    }

    public void updateVariable(String paintableId, String variableName,
                               double newValue, boolean immediate) {
        logger.warning("Update '\" + variableName + \"' variable not supported in offline mode");
    }

    public void updateVariable(String paintableId, String variableName,
                               boolean newValue, boolean immediate) {
        logger.warning("Update '\" + variableName + \"' variable not supported in offline mode");
    }

    public void updateVariable(String paintableId, String variableName,
                               Map<String, Object> map, boolean immediate) {
        logger.warning("Update '\" + variableName + \"' variable not supported in offline mode");
    }

    public void updateVariable(String paintableId, String variableName,
                               String[] values, boolean immediate) {
        logger.warning("Update '\" + variableName + \"' variable not supported in offline mode");
    }

    public void updateVariable(String paintableId, String variableName,
                               Object[] values, boolean immediate) {
        logger.warning("Update '\" + variableName + \"' variable not supported in offline mode");
    }

    public void offlineRegisterConnector(ServerConnector connector, String pid) {
        getConnectorMap(this).registerConnector(pid, connector);
    }

    /**
     * Because connectorMap is private, workaround access to it
     * @param connection
     * @return
     */
    static final native protected ConnectorMap getConnectorMap(ApplicationConnection connection)
    /*-{
        return connection.@com.vaadin.client.ApplicationConnection::connectorMap;
    }-*/;

    public OTooltip getOTooltip() {
        // Expects that replace-with has replaced VTooltip implementation with OTooltip
        return (OTooltip)super.getVTooltip();
    }

}
