package com.jd.edi.auth;

import com.alibaba.druid.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AuthContext {
    private static boolean authEnable = true;
    private static Map<String, String> authConfig = new HashMap<>();
    private static ThreadLocal<Long> currentUser = new ThreadLocal<>();
    private static ThreadLocal<Map<String, String>> authContext = ThreadLocal.withInitial(()->new HashMap<>());

    public static void setAuthConfig(Map<String, String> authConfig) {
        if(authConfig != null) {
            AuthContext.authConfig = new HashMap<>(authConfig);
        }
    }

    public static void setAuthConfigItem(String key, String value) {
        AuthContext.authConfig.put(key, value);
    }

    public static String getAuthConfigItem(String key) {
        return authConfig.get(key);
    }

    public static Map<String, String> getAuthConfig() {
        return Collections.unmodifiableMap(authConfig);
    }

    public static void setCurrentUser(Long currentUser) {
        if(StringUtils.isEmpty(String.valueOf(currentUser))) {
            AuthContext.currentUser.remove();
        }else {
            AuthContext.currentUser.set(currentUser);
        }
    }

    public static Long currentUser(){
        return currentUser.get();
    }
    public static void removeUser(){
        currentUser.remove();
    }
    public static String getAuthContext(String key) {
        return authContext.get().get(key);
    }

    public static Map<String, String> getAuthContext() {
        return Collections.unmodifiableMap(authContext.get());
    }

}
