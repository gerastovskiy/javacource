package ru.cource.thread.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class CacheKey {
    private final String objectHashKey;
    private final String cachedMethodHashKey;

    public CacheKey(Object proxy, Method method) {
        this.objectHashKey = calcObjectHashKey(proxy);
        this.cachedMethodHashKey = calcCachedMethodHashKey(method);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return Objects.equals(objectHashKey, cacheKey.objectHashKey) && Objects.equals(cachedMethodHashKey, cacheKey.cachedMethodHashKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectHashKey, cachedMethodHashKey);
    }

    private String calcObjectHashKey(Object object) {
        var hashCode = String.valueOf(object.hashCode());
        var declaredFields = object.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                declaredField.setAccessible(true);
                hashCode += "#" + declaredField.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return hashCode;
    }
    private String calcCachedMethodHashKey(Method method) {
        return method.getName() + '#' + Arrays.toString(method.getParameters());
    }
}
