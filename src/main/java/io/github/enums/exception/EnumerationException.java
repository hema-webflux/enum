package io.github.enums.exception;

public class EnumerationException extends Exception {
    public EnumerationException(String message) {
        super(message);
    }

    public static void failedEnumeration(String className) throws EnumerationException {
        throw new EnumerationException(String.format("The implementation class [ %s ] must be an enum.", className));
    }
}
