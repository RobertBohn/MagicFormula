package com.magicformula.util;

import com.magicformula.model.Values;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.sql.Date;

public class ClassFactory {

    private static DateTimeFormatter inputDateFormat = DateTimeFormat.forPattern("MM/dd/yyyy");

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

                if (field.getType().getCanonicalName().equals("java.sql.Date")) {
                    DateTime datetime = new DateTime(inputDateFormat.parseMillis(value.getValue()));
                    Date date = new Date(datetime.toDate().getTime());
                    Method method = clazz.getMethod(setterName, new Class[]{Date.class});
                    method.invoke(obj, date);
                }
            }
        }

        return obj;
    }
}
