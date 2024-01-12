package io.github.enums.contracts;

import io.github.enums.exception.EnumerationException;
import io.github.enums.exception.ImplementationException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public interface Mapper {

    default Map<String, Object> toMap() throws ImplementationException, EnumerationException {

        Class<? extends Mapper> reflection = getClass();

        prepareImplementationCheck(reflection);

        return toMap(reflection);
    }

    private void prepareImplementationCheck(Class<?> reflection) throws ImplementationException, EnumerationException {

        if (!reflection.isEnum()) {
            EnumerationException.failedEnumeration(reflection.getSimpleName());
        }

        Class<?> abstractType = Enumerable.class;

        if (!abstractType.isAssignableFrom(reflection)) {
            ImplementationException.failedInherit(abstractType.getSimpleName(), reflection.getSimpleName());
        }

        abstractType = Descriptor.class;

        if (!abstractType.isAssignableFrom(reflection)) {
            ImplementationException.failedInherit(abstractType.getSimpleName(), reflection.getSimpleName());
        }

    }

    default Map<String, Object> toMap(Class<?> element) {

        Map<String, Object> maps = new HashMap<>();

        maps.put("description", invoke(element, "description", true));
        maps.put("value", invoke(element, "value", false));

        return maps;
    }

    private Object invoke(Class<?> element, String method, boolean hasParameter) {
        try {
            return hasParameter
                    ? element.getMethod(method, Field.class).invoke(this, element.getField(this.toString()))
                    : element.getMethod(method).invoke(this);
        } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException exception) {
            throw new RuntimeException();
        }
    }
}