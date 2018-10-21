package org.luksze;

public interface DefaultMethodInterface {

    default String defaultValue() {
        return "defaultValue";
    }
}
