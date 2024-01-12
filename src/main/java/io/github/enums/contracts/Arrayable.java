package io.github.enums.contracts;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public interface Arrayable extends Mapper {

    default Set<Map<String, Object>> toArray() {
        return Arrays.stream(getClass().getEnumConstants())
                .map(e -> {
                    try {
                        return toMap(e.getClass());
                    } catch (NoSuchFieldException ex) {
                        throw new RuntimeException(ex);
                    } catch (InvocationTargetException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoSuchMethodException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(Collectors.toSet());
    }

}
