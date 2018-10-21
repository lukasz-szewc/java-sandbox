package org.luksze;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.luksze.OverriddenDefaultMethodImplementation.OTHER_VALUE;

public class InterfaceDefaultMethodTest {

    private static final String DEFAULT_VALUE = "defaultValue";

    @Test
    public void canInheritDefaultMethodFromInterface() {
        DefaultMethodInterface implementation = new DefaultMethodImplementation();
        assertThat(implementation.defaultValue(), equalTo(DEFAULT_VALUE));
    }

    @Test
    public void canCreateAnonymousImplementationWithDefaultMethod() {
        DefaultMethodInterface implementation = new DefaultMethodInterface() {};
        assertThat(implementation.defaultValue(), equalTo(DEFAULT_VALUE));
    }

    @Test
    public void canOverrideDefaultMethodFromInterface() {
        DefaultMethodInterface implementation = new OverriddenDefaultMethodImplementation();
        assertThat(implementation.defaultValue(), equalTo(OTHER_VALUE));
    }

}
