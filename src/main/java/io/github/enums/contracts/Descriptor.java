package io.github.enums.contracts;

import io.github.enums.annotations.Description;
import io.github.enums.exception.EnumerationException;

import java.lang.reflect.Field;

public interface Descriptor {
    default String description() throws EnumerationException {

        Class<? extends Descriptor> reflector = getClass();

        if (!reflector.isEnum()) {
            EnumerationException.failedEnumeration(reflector.getSimpleName());
        }

        try {
            Field field = reflector.getField(this.toString());

            return description(field);
        }catch (NoSuchFieldException e) {
            throw new EnumerationException("error.");
        }
    }

    default String description(Field element) {

        Description description = element.getAnnotation(Description.class);

        return description == null ? "" : description.value();
    }
}
