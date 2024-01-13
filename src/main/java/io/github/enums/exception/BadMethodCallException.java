package io.github.enums.exception;

final public class BadMethodCallException extends Exception {
    public BadMethodCallException(int code, String message) {
        super(message);
    }
}
