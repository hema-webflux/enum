package hema.web.enums.contracts;

import hema.web.enums.exception.BadMethodCallException;
import hema.web.enums.exception.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

public interface Arrayable extends Mapper {

    default Set<Map<String, Object>> toArray() {

        return Arrays.stream(getClass().getEnumConstants())
                .map(this::getMap)
                .collect(Collectors.toSet());
    }

    private Map<String, Object> getMap(Mapper mapper) {
        try {
            return mapper.toMap();
        } catch (NotImplementedException | BadMethodCallException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
