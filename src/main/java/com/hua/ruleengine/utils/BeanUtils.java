package com.hua.ruleengine.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {

    public static Map<String,Object> convertToMap(Object object){
        Map<String,Object> map = new HashMap<>();

        return map;
    }

    public static void convertObjectMap(Object object,String prefix,Map<String,Object> map){
        if(object == null){
            return;
        }

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
        }
    }


}
