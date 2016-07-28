package com.kaishengit.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Father<T,PK> {

    public Father() {
        Class<?> clazz = this.getClass(); // son
        ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] types = type.getActualTypeArguments();
        Class<?> paramClass = (Class<?>) types[0];
        System.out.println(paramClass);
    }

}
