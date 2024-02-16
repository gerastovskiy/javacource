package ru.cource.reflection;

import ru.cource.reflection.cache.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ObjectInvocationHandler implements InvocationHandler {
    private final Object object;
    private final Cachable caches;
    public ObjectInvocationHandler(Object object) {
        this.object = object;
        this.caches = new Cache();
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Mutator.class)) {
            Arrays.stream(method.getDeclaringClass().getDeclaredMethods())
                    .filter(meth -> meth.isAnnotationPresent(Cached.class))
                    .map(meth -> meth.getName() + '#' + Arrays.toString(meth.getParameters()))
                    .forEach(cacheKey -> caches.removeCache(cacheKey));
        }
        if (method.isAnnotationPresent(Cached.class)) {
            var key = method.getName() + '#' + Arrays.toString(method.getParameters());
            if (caches.containsKey(key))
                return caches.getCache(key);

            var value = method.invoke(object, args);
            caches.putCache(key, value);
            return value;
        }
        return method.invoke(object, args);
    }
}
