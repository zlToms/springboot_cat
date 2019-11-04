package com.tang.zhen.film.comtroller.common;

import lombok.Data;

public final class TraceUtil {

    private TraceUtil(){}

    //springMVC是线程安全的，每一次请求都会有一个线程创建
    private static ThreadLocal<String> threadLocal
             = new ThreadLocal<>();

    public static void initThread(String userId){
        threadLocal.set(userId);
    }
  public static String getUserId(){
        return threadLocal.get();
    }



}
