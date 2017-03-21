package com.magicformula.util;

import com.magicformula.model.Values;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ClassFactory {

    public static Object create(java.lang.Class clazz, Values[] values) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        Object obj = clazz.newInstance();

        Set<String> fieldNames = new HashSet();
        for (Field field : clazz.getFields()) {
            fieldNames.add(field.getName());
        }

        for (Values value : values) {
            String fieldName = value.getField();

            // only process data fields that exist in the target class
            if (fieldNames.contains(fieldName)) {
                Field field = clazz.getField(fieldName);
                String setterName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);

                if (field.getType().getCanonicalName().equals("java.lang.Double")) {
                    Method method = clazz.getMethod(setterName, new Class[]{Double.class});
                    method.invoke(obj, Double.parseDouble(value.getValue()));
                }

                if (field.getType().getCanonicalName().equals("java.lang.String")) {
                    Method method = clazz.getMethod(setterName, new Class[]{String.class});
                    method.invoke(obj, value.getValue());
                }
            }
        }

        return obj;
    }
}
