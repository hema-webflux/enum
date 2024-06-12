import hema.web.enums.annotations.Description;
import hema.web.enums.contracts.Arrayable;
import hema.web.enums.contracts.Descriptor;
import hema.web.enums.contracts.Enumerable;

enum ResponseEnum implements Descriptor, Arrayable, Enumerable<Integer> {

    @Description(desc = "System error.", note = "check code.")
    SYSTEM_ERROR(500),

    @Description(desc = "Unauthorized.", note = "check token is expires.")
    UNAUTHORIZED(401);

    private final int value;

    ResponseEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
