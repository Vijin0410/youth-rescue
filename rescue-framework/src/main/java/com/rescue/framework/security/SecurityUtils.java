package com.rescue.framework.security;

/**
 * 安全工具类
 */
public class SecurityUtils {

    /**
     * 获取当前用户ID
     */
    public static Long getUserId() {
        return CurrentUserHolder.getUserId();
    }

    /**
     * 获取当前用户名
     */
    public static String getUsername() {
        return CurrentUserHolder.getUsername();
    }
}
