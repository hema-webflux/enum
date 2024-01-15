package github.hema.web.enums.contracts;

import github.hema.web.enums.exception.BadMethodCallException;
import github.hema.web.enums.exception.NotImplementedException;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public interface Mapper {

    /**
     * Get the enum as an map.
     * @return
     * @throws NotImplementedException
     * @throws BadMethodCallException
     */
    default Map<String, Object> toMap() throws NotImplementedException, BadMethodCallException {

        Class<? extends Mapper> reflection = getClass();

        this.checkEnumClassImplement(reflection);

        try {

            Map<String, Object> maps = new HashMap<>();

            maps.put("description", reflection.getMethod("description").invoke(this));

            maps.put("value", reflection.getMethod("value").invoke(this));

            return maps;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
            throw new BadMethodCallException(500, exception.getMessage());
        }
    }

    private void checkEnumClassImplement(Class<?> reflection) throws NotImplementedException {
        Class<?> abstractType = Enumerable.class;

        if (!abstractType.isAssignableFrom(reflection)) {
            NotImplementedException.failedInherit(abstractType.getSimpleName(), reflection.getSimpleName());
        }

        abstractType = Descriptor.class;

        if (!abstractType.isAssignableFrom(reflection)) {
            NotImplementedException.failedInherit(abstractType.getSimpleName(), reflection.getSimpleName());
        }
    }

}