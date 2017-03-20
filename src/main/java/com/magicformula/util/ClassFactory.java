package com.magicformula.util;

import com.magicformula.model.Values;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by robert on 3/19/17.
 */
public class ClassFactory {

    public static Object create(java.lang.Class clazz, Values[] values) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Object obj = clazz.newInstance();

        for (Values value : values) {
            String fieldName = value.getField();
            fieldName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
            Method method = clazz.getMethod("set" + fieldName, new Class[]{String.class});
            method.invoke(obj, value.getValue());
        }

        return obj;
    }
}
