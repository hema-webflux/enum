package io.github.enums.contracts;

import java.util.*;
import java.util.stream.Collectors;

public interface Arrayable extends Mapper {

    default Set<Map<String, Object>> toArray() {
        return Arrays.stream(getClass().getEnumConstants())
                .map(e -> toMap(e.getClass()))
                .collect(Collectors.toSet());
    }

}
