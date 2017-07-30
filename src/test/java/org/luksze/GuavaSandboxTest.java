package org.luksze;

import org.junit.Test;
import org.luksze.builder.Collections;

import java.util.List;

public class GuavaSandboxTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testName() throws Exception {
        //given
        List<Integer> integers = Collections.of(1).immutableList();

        //when
        integers.add(2);

    }
}
