package com.asj.api.utils;

import java.lang.reflect.Field;

public class ObjectUpdater {

    public static void updateNonNullFields(Object existingObject, Object newObject) {
        try {
            for (Field field : existingObject.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object newValue = field.get(newObject);
                
                if (newValue != null) {
                    field.set(existingObject, newValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
