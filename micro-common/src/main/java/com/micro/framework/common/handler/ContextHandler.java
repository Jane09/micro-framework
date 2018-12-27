package com.micro.framework.common.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tb
 * @date 2018/12/27 16:42
 */
public final class ContextHandler {

    private static ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<>();

    private static void set(String key, Object value) {
        Map<String,Object> map = threadLocal.get();
        if(null == map) {
            map = new HashMap<>(16);
            threadLocal.set(map);
        }
        map.put(key,value);
    }

    private static Object get(String key) {
        Map<String,Object> map = threadLocal.get();
        if(null == map) {
            map = new HashMap<>(16);
            threadLocal.set(map);
        }
        return map.get(key);
    }

    private static String objectToString(Object value) {
        return null == value?"":String.valueOf(value);
    }
}
