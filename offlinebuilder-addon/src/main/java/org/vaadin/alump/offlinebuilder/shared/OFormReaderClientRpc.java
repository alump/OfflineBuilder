/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.shared;

import com.vaadin.shared.communication.ClientRpc;

/**
 * Created by alump on 19/09/14.
 */
public interface OFormReaderClientRpc extends ClientRpc {
    void readFormData(String formId);
}
