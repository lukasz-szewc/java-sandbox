package org.luksze;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SomeNotFullyCoveredClassTest {

    @Test
    public void shouldHitFirstCondition() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean string = sut.complexCondition("abc");

        assertFalse(string);

    }

    @Test
    public void shouldHitSecondCondition() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean string = sut.complexCondition("abcdef");

        assertFalse(string);

    }

    @Test
    public void shouldHitThirdCondition() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean string = sut.complexCondition("boston");

        assertTrue(string);

    }

    @Test
    public void shouldHitThirdAndFourtCondition() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean string = sut.complexCondition("barbarossa");

        assertTrue(string);

    }

}