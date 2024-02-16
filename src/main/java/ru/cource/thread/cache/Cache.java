package ru.cource.thread.cache;

import ru.cource.thread.clock.Clockable;
import java.util.HashMap;

public class Cache implements Cachable <CacheKey,Object>{
    private final HashMap<CacheKey, CacheValue> values;
    public Cache() {
        this.values = new HashMap<>();
    }
    @Override
    public void clear() { this.values.clear(); }

    @Override
    public Object getCache(CacheKey key, Clockable clock) {
        if (values.containsKey(key)) {
            if (values.get(key).isActual(clock)) {
                System.out.println("getCache: " + key + ": " + values.get(key).getValue(clock));
                return values.get(key).getValue(clock);
            }
            values.remove(key);
        }
        return null;
    }
    @Override
    public void removeCache(CacheKey key) {
        System.out.println("removeCache: " + key);
        values.remove(key);
    }
    @Override
    public void putCache(CacheKey key, Object value, int lifeTime, Clockable clock) {
        System.out.println("putCache: " + key + ": " + value);
        values.put(key, new CacheValue(value, lifeTime, clock));
    }
}