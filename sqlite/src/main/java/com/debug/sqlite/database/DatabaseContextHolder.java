package com.debug.sqlite.database;

/**
 * 线程缓存数据源
 * @author Lyb
 * @since 2019-03-20
 */
public class DatabaseContextHolder {
    /**
     * 线程安全的DatabaseType容器
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDatabaseType(String type) {
        CONTEXT_HOLDER.set(type);
    }

    public static String getDatabaseType() {
        return CONTEXT_HOLDER.get();
    }

    public static void removeDatabaseType() {
        CONTEXT_HOLDER.remove();
    }
}
