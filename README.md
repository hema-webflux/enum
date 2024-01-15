# Enum

#### Simple

```java
import io.github.hema.enums.annotations.Description;
import io.github.hema.enums.contracts.Descriptor;
import io.github.hema.enums.contracts.Enumerable;
import io.github.hema.enums.contracts.Arrayable;

public enum Color implements Descriptor, Arrayable, Enumerable<String> {
    @Description("红色")
    Red("red"),

    @Description("蓝色")
    Blue("blue");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}

Color.Red.

toMap(); //   { "description": "红色", "value": "red" }

Color.Red.

toArray(); // [{ "description": "红色", "value": "red" },{ "description": "蓝色", "value": "blue" }]

```