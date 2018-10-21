package org.luksze;

import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class YearOfLifeTest {

    @Test
    public void happyPathTest() {
        //given
        YearOfLife yearOfLife = new YearOfLife(5);

        //when
        YearOfLife nextOne = yearOfLife.nextOne();

        //then
        assertThat(nextOne.isOlder(yearOfLife), is(TRUE));
        assertThat(yearOfLife.isOlder(nextOne), is(FALSE));
        assertThat(nextOne, is(nextOne));
        assertThat(yearOfLife, is(new YearOfLife(5)));
        assertThat(yearOfLife.hashCode(), equalTo(new YearOfLife(5).hashCode()));
    }
}