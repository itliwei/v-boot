package io.github.itliwei.vboot.vtool.lock;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 根据key来锁定
 *
 * @author lang.zhou
 * @date 2022/3/22 17:45
 */
public class KeyLock {
    private static Map<String, Object> lock = new ConcurrentHashMap<>();

        /**
         * 尝试对key加一个锁，返回加锁成功或失败
         */
    public static void lock(String key) {
        Object my = new Object();
        Object o = null;
        while (o == null || my != o) {
            o = lock.computeIfAbsent(key, l -> my);
        }
    }

    /**
     * 尝试对key加一个锁，返回加锁成功或失败
     */
    public static void lock(Set<String> key) {
        if (key.size() == 1) {
            Iterator<String> iterator = key.iterator();
            if (iterator.hasNext()) {
                lock(iterator.next());
            }
            return;
        }
        TreeSet<String> treeSet = new TreeSet<>((String::compareTo));
        treeSet.addAll(key);
        Object my = new Object();
        for (String s : treeSet) {
            Object o = null;
            while (o == null || my != o) {
                o = lock.computeIfAbsent(s, l -> my);
            }
        }
    }

    public static void unlock(String key) {
        lock.remove(key);
    }

    public static void unlock(Set<String> key) {
        key.forEach(c -> {
            lock.remove(c);
        });
    }
}