package org.luksze;

public interface DefaultMethodInterface {

    String DEFAULT_VALUE = "defaultValue";

    default String defaultValue() {
        return DEFAULT_VALUE;
    }
}
