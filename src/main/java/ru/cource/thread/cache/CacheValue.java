package ru.cource.thread.cache;

import lombok.Setter;
import ru.cource.thread.clock.Clockable;

class CacheValue {
    private final Object value;
    @Setter
    private long createCacheTime;
    private final int cacheLifeTimeMillis;

    CacheValue(Object value, int lifeTime, Clockable clock) {
        this.value = value;
        this.cacheLifeTimeMillis = lifeTime;
        this.createCacheTime = clock.currentTimeMillis();
    }
    Object getValue(Clockable clock) {
        setCreateCacheTime(clock.currentTimeMillis());
        return value;
    }
    boolean isActual(Clockable clock){
        return (clock.currentTimeMillis() - createCacheTime < cacheLifeTimeMillis);
    }
}