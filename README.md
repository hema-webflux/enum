# Enum

#### Simple

```java
import hema.web.enums.annotations.Description;
import hema.web.enums.contracts.Descriptor;
import hema.web.enums.contracts.Enumerable;
import hema.web.enums.contracts.Arrayable;

public enum Response implements Descriptor, Arrayable, Enumerable<Integer> {
    
    @Description(desc = "system error", note = "checked your code.")
    SYSTEM_EXCEPTION(1000);

    private final Integer value;

    Response(Integer value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}

Response.SYSTEM_EXCEPTION.toMap(); //   { "description": "system error", "value": 1000 }

Response.SYSTEM_EXCEPTION.toArray(); // [{ "description": "system error", "value": 1000 }]

```