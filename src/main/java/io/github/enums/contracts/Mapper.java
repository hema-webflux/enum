package io.github.enums.contracts;

import io.github.enums.annotations.Description;
import io.github.enums.exception.EnumerationException;
import io.github.enums.exception.ImplementationException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public interface Mapper {

    default Map<String, Object> toMap() throws ImplementationException, EnumerationException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

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

    default Map<String, Object> toMap(Class<?> element) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        Map<String, Object> maps = new HashMap<>();

        maps.put("description",MethodCaller.make(true).action(element,"description"));
        maps.put("value",MethodCaller.make(true).action(element,"value"));

        return maps;
    }

    sealed interface MethodCaller {

        static MethodCaller make(boolean hasParameter) {
            return hasParameter ? new ParameterMethodCaller() : new NullableMethodCaller();
        }

        default Object invoke(Class<?> element, String method) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            return action(element, method);
        }

        Object action(Class<?> element, String method) throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException;

        final class ParameterMethodCaller implements MethodCaller {

            @Override
            public Object action(Class<?> element, String name) throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {
                Method method = element.getMethod(name, Field.class);

                Field field = element.getField(this.toString());

                return method.invoke(this, field);
            }
        }

        final class NullableMethodCaller implements MethodCaller {
            @Override
            public Object action(Class<?> element, String method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
                return element.getMethod(method).invoke(this);
            }
        }
    }
}
