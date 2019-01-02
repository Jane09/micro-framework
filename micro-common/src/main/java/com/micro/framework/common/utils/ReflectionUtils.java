package com.micro.framework.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author tb
 * @date 2018/12/27 17:07
 */
@Slf4j
public final class ReflectionUtils {

    private static final String SETTER_PREFIX = "set";
    private static final String GETTER_PREFIX = "get";
    private static final String CGLIB_CLASS_SEPARATOR = "$$";




    private static Object invokeMethod(final Object target, final String methodName, final Class<?>[] parameterTypes, final Object[] args) {
        Method method = getAccessibleMethod(target,methodName,parameterTypes);
        Validate.notNull(method,"Could not find method ["+methodName+" ] on target [ "+target+" ]");
        try {
            return method.invoke(target,args);
        } catch (IllegalAccessException | InvocationTargetException e) {
           throw newException(e);
        }
    }

    private static RuntimeException newException(Exception e) {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException(e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException(((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException("Unexpected Checked Exception.", e);
    }

    private static Method getAccessibleMethod(final Object target, final String methodName, final Class<?>[] parameterTypes) {
        Validate.notNull(target, "Target can not be null");
        Validate.notBlank(methodName, "Method Name can not be blank");
        for (Class<?> searchType = target.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
            try {
                Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
                makeAccessible(method);
                return method;
            } catch (NoSuchMethodException ignored) {
            }
        }
        return null;
    }

    private static void makeAccessible(final Method method) {
        boolean accessible = (!Modifier.isPublic(method.getModifiers()) ||
                !Modifier.isPublic(method.getDeclaringClass().getModifiers())) &&
                !method.isAccessible();
        if (accessible) {
            method.setAccessible(true);
        }
    }
}
