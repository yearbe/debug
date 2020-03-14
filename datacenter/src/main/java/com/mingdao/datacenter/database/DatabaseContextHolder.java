package com.mingdao.datacenter.database;

/**
 * 数据库切换上下文
 * @author Lyb
 * @since 2019-03-21
 */
public class DatabaseContextHolder {
    /**
     * 线程安全的Database容器
     */
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static void setDatabase(String type) {
        HOLDER.set(type);
    }

    public static String getDatabase() {
        return HOLDER.get();
    }

    public static void removeDatabase() {
        HOLDER.remove();
    }
}
