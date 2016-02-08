package org.luksze;

class DefaultMethodImplementation implements DefaultMethodInterface {

    static class OverriddenDefaultMethodImplementation extends DefaultMethodImplementation {

        public static final String OTHER_VALUE = "OtherValue";

        @Override
        public String defaultValue() {
            return OTHER_VALUE;
        }
    }
}

