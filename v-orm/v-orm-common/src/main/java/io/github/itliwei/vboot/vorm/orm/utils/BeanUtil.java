package io.github.itliwei.vboot.vorm.orm.utils;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : liwei
 * @date : 2019/05/11 15:56
 */
public class BeanUtil {
    /**
     * 获取属性名数组
     * */
    public static List<String> getFiledNames(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        return Arrays.stream(fields).map(Field::getName).collect(Collectors.toList());
    }

    /* 根据属性名获取属性值
    * */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            return method.invoke(o, new Object[] {});
        } catch (Exception e) {
            return null;
        }
    }

    public static Class<?>[] findGenericType(Class<?> thisClass, Class<?> clazz) {
        List<Type[]> typesList = Arrays.stream(thisClass.getGenericInterfaces())
                .map(type -> {
                    if (type instanceof ParameterizedType) {
                        return (ParameterizedType) type;
                    }
                    return null;
                })
                .filter(parameterizedType -> parameterizedType != null && parameterizedType.getRawType() == clazz)
                .map(ParameterizedType::getActualTypeArguments)
                .collect(Collectors.toList());
        if (typesList.isEmpty()) {
            typesList.add(new Type[]{Object.class});
        }
        Type[] types = typesList.get(0);

        return Arrays.stream(types)
                .map(type -> {
                    if (type instanceof ParameterizedType) {
                        return ((ParameterizedType) type).getRawType();
                    }
                    return type;
                })
                .toArray(Class<?>[]::new);
    }

    public static <T> List<T> entityList(Class<T> clazz, List<Map<String, Object>> list) {
        List<T> ts = Lists.newArrayListWithCapacity(list.size());
        for (Map<String, Object> result : list) {
            ts.add(hashToObject(result, clazz));
        }
        return ts;
    }

    public static <T> T hashToObject(Map<String, Object> map, Class<T> clazz) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        try {
            T obj = clazz.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
            return obj;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
