package hema.web.enums.contracts;

import hema.web.enums.annotations.Description;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Function;

public interface Descriptor {

    default String description() {
        return callDescription(description -> Optional.ofNullable(description.desc()).orElse(""));
    }

    default String note() {
        return callDescription(description -> Optional.ofNullable(description.note()).orElse(""));
    }

    private String callDescription(Function<Description, String> closure) {
        Class<? extends Descriptor> reflector = getClass();

        try {
            Field field = reflector.getField(this.toString());

            Description description = field.getAnnotation(Description.class);

            return closure.apply(description);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException();
        }
    }
}
