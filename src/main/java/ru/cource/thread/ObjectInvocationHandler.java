package ru.cource.thread;

import ru.cource.thread.cache.Cachable;
import ru.cource.thread.cache.Cache;
import ru.cource.thread.cache.CacheKey;
import ru.cource.thread.clock.Clock;
import ru.cource.thread.clock.Clockable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class ObjectInvocationHandler implements InvocationHandler {
    private final Object object;
    private final Cachable caches;
    private final Clockable clock;

    public  ObjectInvocationHandler(Object object, Clockable clock) {
        this.object = object;
        this.caches = new Cache();
        this.clock = clock;
    }

    public  ObjectInvocationHandler(Object object) {
        this.object = object;
        this.caches = new Cache();
        this.clock = new Clock();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnValue;

        synchronized (object) {
            if (method.isAnnotationPresent(Cached.class)) {
                var cacheKey = new CacheKey(object, method);

                // return cached value if exists
                returnValue = caches.getCache(cacheKey, clock);
                if (returnValue != null) return returnValue;

                // invoke method and set cached value
                var value = method.invoke(object, args);
                caches.putCache(cacheKey, value, method.getAnnotation(Cached.class).value(), clock);

                return value;
            }

            return method.invoke(object, args);
        }
    }
}
