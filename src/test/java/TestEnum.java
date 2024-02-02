import hema.web.enums.annotations.Description;
import hema.web.enums.contracts.Arrayable;
import hema.web.enums.contracts.Descriptor;
import hema.web.enums.contracts.Enumerable;

public enum TestEnum implements Descriptor, Arrayable, Enumerable<Integer> {

    @Description("foo")
    FOO(0),

    @Description("bar")
    BAR(1);

    private final int value;

    TestEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
