package framework.common.handler;

import framework.common.constant.Global;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tb
 * @date 2018/12/27 16:42
 */
public final class ContextHandler {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<>(16);
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    private static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (null == map) {
            map = new HashMap<>(16);
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static void remove() {
        threadLocal.remove();
    }

    private static String getValue(String key) {
        return objectToString(get(key));
    }

    private static String objectToString(Object value) {
        return null == value ? "" : String.valueOf(value);
    }


    public static String getUserId() {
        return getValue(Global.CONTEXT_KEY_USER_ID);
    }

    public static String getUsername() {
        return getValue(Global.CONTEXT_KEY_USER_NAME);
    }

    public static String getUser() {
        return getValue(Global.CONTEXT_KEY_USER);
    }

    public static String getToken() {
        return getValue(Global.CONTEXT_KEY_USER_TOKEN);
    }

    public static void setToken(String token) {
        set(Global.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setUsername(String username) {
        set(Global.CONTEXT_KEY_USER_NAME, username);
    }

    public static void setUserID(String userId) {
        set(Global.CONTEXT_KEY_USER_ID, userId);
    }

    public static void setUser(String user) {
        set(Global.CONTEXT_KEY_USER, user);
    }
}
