package org.luksze;

import org.junit.Test;

import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class YearOfLifeTest {

    @Test
    public void happyPathTest() throws Exception {
        //given
        YearOfLife yearOfLife = new YearOfLife(5);

        //when
        YearOfLife nextOne = yearOfLife.nextOne();

        //then
        assertThat(nextOne.isOlder(yearOfLife), is(TRUE));
        assertThat(nextOne, is(nextOne));
        assertThat(yearOfLife, is(yearOfLife));
    }
}