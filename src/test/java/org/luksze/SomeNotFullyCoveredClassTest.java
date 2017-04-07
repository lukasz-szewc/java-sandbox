package org.luksze;

import org.junit.Assert;
import org.junit.Test;

public class SomeNotFullyCoveredClassTest {

    @Test
    public void shouldHitFirstCondition() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean abc = sut.complexCondition("abc");

        Assert.assertFalse(abc);

    }

    @Test
    public void someTest() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean abc = sut.complexCondition("abcdef");

        Assert.assertFalse(abc);

    }

}