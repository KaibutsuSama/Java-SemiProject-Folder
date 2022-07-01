package com.kaibutsusama.reggie.common;

/**
 * 基于ThreadLocal封装工具类，用于保存和获取当前用户的登录id
 * @author KaibutsuSama
 * @date 2022/6/29
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
