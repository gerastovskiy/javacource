package ru.cource.reflection.cache;

import java.util.HashMap;
import java.util.Map;

public class Cache implements Cachable{
    private final Map<Object, Object> values;
    public Cache() {
        this.values = new HashMap<>();
    }
    @Override
    public boolean containsKey(Object key) {
        return values.containsKey(key);
    }
    @Override
    public Object getCache(Object key) {
        System.out.println("getCache: " + key + ": " + values.get(key));
        return values.get(key);
    }
    @Override
    public void removeCache(Object key) {
        System.out.println("removeCache: " + key);
        values.remove(key);
    }
    @Override
    public void putCache(Object key, Object value) {
        System.out.println("putCache: " + key + ": " + value);
        values.put(key, value);
    }
}
