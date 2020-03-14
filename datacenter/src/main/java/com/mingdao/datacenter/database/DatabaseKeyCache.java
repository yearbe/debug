package com.mingdao.datacenter.database;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据源Key缓存
 * @author Lyb
 * @since 2019-03-21
 */
public class DatabaseKeyCache {
    private static final List<String> KEYS = new ArrayList<>();

    public static List<String> getKeys() {
        return KEYS;
    }

    public static void addKey(String key) {
        KEYS.add(key);
    }

    public static void removeKey(String key) {
        KEYS.remove(key);
    }
}
