package hema.web.enums.contracts;

import hema.web.enums.annotations.Description;

import java.lang.reflect.Field;

public interface Descriptor {

    default String description() {

        Class<? extends Descriptor> reflector = getClass();

        try {
            Field field = reflector.getField(this.toString());

            Description description = field.getAnnotation(Description.class);

            return description == null ? "" : description.value();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException();
        }
    }
}
