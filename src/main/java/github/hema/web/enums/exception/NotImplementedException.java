package github.hema.web.enums.exception;

public class NotImplementedException extends Exception {
    public NotImplementedException(String message) {
        super(message);
    }

    public static void failedInherit(String parentName, String className) throws NotImplementedException {
        throw new NotImplementedException(String.format("Enum class [ %s ] must implement [ %s ].", className, parentName));
    }
}
