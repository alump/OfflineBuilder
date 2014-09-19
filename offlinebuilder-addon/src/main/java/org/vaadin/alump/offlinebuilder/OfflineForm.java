/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder;

import com.vaadin.ui.AbstractComponent;
import org.json.JSONException;
import org.vaadin.alump.offlinebuilder.shared.OFormState;
import org.vaadin.alump.offlinebuilder.shared.OFormState.Field;
import org.vaadin.alump.offlinebuilder.shared.OFormState.FieldType;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by alump on 19/09/14.
 */
public class OfflineForm extends AbstractComponent {

    public static class FieldNotFound extends RuntimeException {
        public FieldNotFound(String field) {
            super("Field " + field + " not found");
        }
    }

    public OfflineForm() {
        this(UUID.randomUUID().toString());
    }

    public OfflineForm(String formId) {
        setFormId(formId);
    }

    protected OFormState getState() {
        return (OFormState) super.getState();
    }

    public void setFormId(String id) {
        getState().formId = id;
    }

    public String getFormId() {
        return getState().formId;
    }

    /**
     * Add field to form
     * @param type Type of field
     * @return ID of field
     */
    public String addField(FieldType type) {
        return addField(type, null);
    }

    /**
     * Add field to form
     * @param id ID of field
     * @param type Type of field
     * @return ID of field
     */
    public String addField(FieldType type, String id) {
        if(hasField(id)) {
            throw new IllegalStateException("Form already has field " + id);
        }
        Field field = new Field();
        if(id != null) {
            field.id = id;
        }
        field.type = type;
        getState().fields.add(field);

        return field.id;
    }

    /**
     * Set value of field
     * @param id
     * @param value
     * @throws FieldNotFound
     */
    public void setValue(String id, Boolean value) throws FieldNotFound {
        getField(id).value = value.toString();
    }

    public void setValue(String id, String value) throws FieldNotFound {
        getField(id).value = value.toString();
    }

    /**
     * Checks if form has given field
     * @param id
     * @return
     */
    public boolean hasField(String id) {
        for(Field field : getState().fields) {
            if(field.id.equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get field with given ID
     * @param id
     * @return
     * @throws FieldNotFound
     */
    protected Field getField(String id) throws FieldNotFound {
        for(Field field : getState().fields) {
            if(field.id.equals(id)) {
                return field;
            }
        }
        throw new FieldNotFound(id);
    }
}
