/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.shared;

import com.vaadin.shared.communication.ServerRpc;

import java.util.Map;

/**
 * Created by alump on 19/09/14.
 */
public interface OFormReaderServerRpc extends ServerRpc {

    void onDataRead(String formId, Map<String,String> data);
}
