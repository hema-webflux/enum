package io.github.enums.contracts;

import io.github.enums.annotations.Description;
import io.github.enums.exception.EnumerationException;
import io.github.enums.exception.ImplementationException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public interface Mapper {

    default void toMap() throws EnumerationException, ImplementationException, NoSuchFieldException, NoSuchMethodException {
        Class<? extends Mapper> reflectionClass = getClass();

        checkImplementation(reflectionClass);

        toMap(reflectionClass.getField(this.toString()));

        Arrays.stream(reflectionClass.getEnumConstants()).forEach(e -> {

            try {
                Method method = e.getClass().getMethod("description");
                System.out.println("method");
                System.out.println(method);
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

    private void checkImplementation(Class<?> reflectionClass) throws ImplementationException, EnumerationException {

        if (!reflectionClass.isEnum()) {
            EnumerationException.failedEnumeration(reflectionClass.getSimpleName());
        }

        Class<?> abstractType = Enumerable.class;

        if (!abstractType.isAssignableFrom(reflectionClass)) {
            ImplementationException.failedInherit(abstractType.getSimpleName(), reflectionClass.getSimpleName());
        }

        abstractType = Descriptor.class;

        if (!abstractType.isAssignableFrom(reflectionClass)) {
            ImplementationException.failedInherit(abstractType.getSimpleName(), reflectionClass.getSimpleName());
        }

    }


    default Map<String, Object> toMap(Field element) throws NoSuchMethodException {

        Map<String, Object> maps = new HashMap<>();



        try {
            Method method = element.getClass().getMethod("description");
        }catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }


        return maps;
    }

    private MethodCaller caller(boolean hasParameter) {
        return hasParameter
                ? new MethodCaller.ParameterMethodCaller()
                : new MethodCaller.NullableMethodCaller();
    }

    interface MethodCaller {
        default Object invoke(Field element, String name) {
            try {
                return action(element, name);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(String.format("The call method [ %s ] is incorrect.", name));
            }
        }

        Object action(Field element, String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

        class ParameterMethodCaller implements MethodCaller {

            @Override
            public Object action(Field element, String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
                Method method = element.getClass().getMethod(name, Field.class);
                System.out.printf("Method: %s", method);
                return "";
            }
        }

        class NullableMethodCaller implements MethodCaller {

            @Override
            public Object action(Field element, String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
                Method method = element.getClass().getMethod(name);
                System.out.printf("Method: %s", method);

                return "";
            }
        }
    }
}
