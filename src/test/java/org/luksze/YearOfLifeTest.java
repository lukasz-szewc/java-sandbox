package org.luksze;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class YearOfLifeTest {

    private YearOfLife matureYear;

    @Before
    public void setUp() {
        matureYear = new YearOfLife(18);
    }

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

    @Test
    public void equalMethodTest() {
        assertEquals(matureYear, matureYear);
        assertNotEquals(matureYear, new Object());
        assertNotEquals(matureYear, null);
        assertEquals(new YearOfLife(18), new YearOfLife(18));

    }
}