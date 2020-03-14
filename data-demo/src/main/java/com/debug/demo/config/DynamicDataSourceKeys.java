package com.debug.demo.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lyb
 * @since 2020-01-20
 */
public final class DynamicDataSourceKeys {
    private static final Set<String> keys = new HashSet<>();
    static void addKeys(Collection<String> addKeys) {
        keys.addAll(addKeys);
    }
    public static boolean existKey(String key) {
        return keys.contains(key);
}
}