package ru.cource.reflection;

import java.lang.reflect.Proxy;

public abstract class Utils {
    public static <T> T cache(T instance){
        return (T) Proxy.newProxyInstance(
                    instance.getClass().getClassLoader(),
                    instance.getClass().getInterfaces(),
                    new ObjectInvocationHandler(instance)
                    );
    }
}
