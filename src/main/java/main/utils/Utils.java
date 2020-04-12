package main.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class.toString());

    public static Map<String,Object> findUpdatedFields(Object object){

        List<Field> list = Arrays.asList(object.getClass().getDeclaredFields());
        Map<String,Object> map = new HashMap<>();
        list.forEach(x->{
            x.setAccessible(true);
            try {
                map.put(x.getName(),x.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        Map<String, Object> result = map.entrySet()
                .stream()
                .filter(x->x.getValue() instanceof Integer ? (Integer) x.getValue()!=0 : x.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return result;

    }



    public static Object prepareUpdateObject(Map<?,?> values, Object newObject, Object oldObject) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Field[] field = newObject.getClass().getDeclaredFields();
        for (Field field1 : field) {
            if(!values.containsKey(field1.getName()))
            {
                field1.setAccessible(true);
                Object f = new PropertyDescriptor(field1.getName(), newObject.getClass()).getReadMethod().invoke(oldObject);
                field1.set(newObject,f);
            }
        }
        for(Field f1 : field)
        {
            f1.setAccessible(true);
            Object o = f1.get(newObject);
            System.out.println("value: "+o);
        }

        return newObject;
    }



}