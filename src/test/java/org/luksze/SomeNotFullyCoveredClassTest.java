package org.luksze;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class SomeNotFullyCoveredClassTest {

    @Test
    public void shouldHitWholeCondition() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean string = sut.complexCondition("barbarossa");

        assertTrue(string);
    }

    @Test
    public void shouldHitFirstPartOfCondition() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean string = sut.complexCondition("sign");

        assertFalse(string);
    }

    @Test
    public void shouldHitSecondPartOfCondition() throws Exception {
        SomeNotFullyCoveredClass sut = new SomeNotFullyCoveredClass();

        boolean string = sut.complexCondition("Marcus Aurelius Emperor and philosopher");

        assertFalse(string);
    }

}