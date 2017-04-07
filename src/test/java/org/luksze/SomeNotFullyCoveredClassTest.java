package org.luksze;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SomeNotFullyCoveredClassTest {

    @Test
    public void shouldHitThirdAndFourtCondition() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean string = sut.complexCondition("barbarossa");

        assertTrue(string);

    }

}