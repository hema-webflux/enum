package io.github.enums.exception;

public class ImplementationException extends Exception {
    public ImplementationException(String message) {
        super(message);
    }

    public static void failedInherit(String parentName, String className) throws ImplementationException {
        throw new ImplementationException(String.format("Enum [ %s ] must implement [ %s ].", className, parentName));
    }
}
