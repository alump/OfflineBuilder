/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.ui.Field;
import com.vaadin.client.ui.VTextField;

import com.google.gwt.dom.client.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Not working yet, have to rewrite these field components *sadface*
 */
public class OTextField extends TextBoxBase implements Field, ChangeHandler,
        FocusHandler, BlurHandler, KeyDownHandler {

    private final static Logger logger = Logger.getLogger(OTextField.class.getName());

    /**
     * Interface to implement to get all high level events of OTextField
     */
    public interface OTextFieldListener {
        void onValueChanged(String value);
        void onCurPosChange(int pos);
        void onBlur();
        void onFocus();
    }

    private final List<OTextFieldListener> listeners = new ArrayList<OTextFieldListener>();

    public OTextField() {
        this(Document.get().createTextInputElement());
    }

    protected OTextField(Element root) {
        super(root);
        setStyleName(VTextField.CLASSNAME);
        addChangeHandler(this);
        if (BrowserInfo.get().isIE() || BrowserInfo.get().isFirefox()) {
            addKeyDownHandler(this);
        }
        addFocusHandler(this);
        addBlurHandler(this);
    }

    @Override
    public void onBlur(BlurEvent event) {

    }

    @Override
    public void onChange(ChangeEvent event) {

    }

    @Override
    public void onFocus(FocusEvent event) {

    }

    @Override
    public void onKeyDown(KeyDownEvent event) {

    }

    public void addOTextFieldListener(OTextFieldListener listener) {
        listeners.add(listener);
    }

    public void removeOTextFieldListener(OTextFieldListener listener) {
        listeners.remove(listener);
    }

}
