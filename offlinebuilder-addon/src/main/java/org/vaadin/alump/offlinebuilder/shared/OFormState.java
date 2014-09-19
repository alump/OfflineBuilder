/*
 * OfflineBuilder
 *
 * Copyright (c) 2014 Sami Viitanen <alump@vaadin.com>
 *
 * See LICENSE.txt
 */

package org.vaadin.alump.offlinebuilder.shared;

import com.vaadin.shared.AbstractComponentState;

import java.util.ArrayList;
import java.util.List;

/**
 * State class for offline form
 */
public class OFormState extends AbstractComponentState {

    public static enum FieldType {
        TEXT, CHECKBOX;
    }

    public static enum LayoutType {
        VERTICAL, FORM;
    }

    public static class Field {
        public String id;
        public FieldType type;
        public String value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Field field = (Field) o;

            if (!id.equals(field.id)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public String formId;
    public LayoutType layout = LayoutType.VERTICAL;
    public List<Field> fields = new ArrayList<Field>();
}
