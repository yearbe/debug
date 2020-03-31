package com.debug.sqlite.database;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Lyb
 * @since 2019-03-20
 */
public class DatabaseKey {
    private static final Set<String> KEYS = new HashSet<>();

    public static Set<String> getKeys() {
        return DatabaseKey.KEYS;
    }

    public static void addKey(String key) {
        DatabaseKey.KEYS.add(key);
    }

    public static void setKeys(Set<String> keys) {
        DatabaseKey.KEYS.clear();
        DatabaseKey.KEYS.addAll(keys);
    }

    public static void removeKey(String key) {
        DatabaseKey.KEYS.remove(key);
    }

    public static void clear() {
        DatabaseKey.KEYS.clear();
    }
}
