package ru.cource.thread.cache;

import ru.cource.thread.clock.Clockable;

public interface Cachable <K,V>{
    V getCache(K key, Clockable clock);
    void removeCache(K key);
    void putCache(K key, V value, int lifeTime, Clockable clock);
    void clear();
}
