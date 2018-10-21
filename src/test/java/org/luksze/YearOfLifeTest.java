package org.luksze;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class YearOfLifeTest {

    private YearOfLife matureYear;

    @Before
    public void setUp() {
        matureYear = new YearOfLife(18);
    }

    @Test
    public void equalsMethodTest() {
        assertEquals(matureYear, matureYear);
        assertNotEquals(matureYear, new Object());
        assertNotEquals(matureYear, null);
        assertEquals(new YearOfLife(18), new YearOfLife(18));
    }
}