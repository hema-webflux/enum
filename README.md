# Enum

#### Simple

```java
import hema.web.enums.annotations.Description;
import hema.web.enums.contracts.Descriptor;
import hema.web.enums.contracts.Enumerable;
import hema.web.enums.contracts.Arrayable;

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