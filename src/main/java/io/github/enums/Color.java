package io.github.enums;

import io.github.enums.annotations.Description;
import io.github.enums.contracts.Descriptor;
import io.github.enums.contracts.Enumerable;
import io.github.enums.contracts.Arrayable;

public enum Color implements Descriptor, Arrayable, Enumerable<String> {
    @Description("Color:red")
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
