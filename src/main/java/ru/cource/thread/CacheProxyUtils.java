package ru.cource.thread;

import ru.cource.thread.clock.Clockable;
import java.lang.reflect.Proxy;

public abstract class CacheProxyUtils {
    public static <T> T cache(T instance, Clockable clock){
        return (T) Proxy.newProxyInstance(
                                instance.getClass().getClassLoader(),
                                instance.getClass().getInterfaces(),
                                new ObjectInvocationHandler(instance, clock)
                                );
    }
    public static <T> T cache(T instance){
        return (T) Proxy.newProxyInstance(
                instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(),
                new ObjectInvocationHandler(instance)
        );
    }
}