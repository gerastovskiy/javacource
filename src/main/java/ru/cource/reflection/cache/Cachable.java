package ru.cource.reflection.cache;

public interface Cachable {
    Object getCache(Object key);
    boolean containsKey(Object key);
    void removeCache(Object key);
    void putCache(Object key, Object value);
}
