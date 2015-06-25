package org.luksze;

import org.junit.Test;

import java.time.LocalDate;

import static java.lang.Boolean.TRUE;
import static java.time.LocalDate.of;
import static java.time.Month.JUNE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NewJodaTimeTest {
    @Test
    public void canCreateLocalDate() throws Exception {
        LocalDate date = of(2016, JUNE, 23);
        assertThat(date.isLeapYear(), is(TRUE));
        assertThat(date.getMonth(), is(JUNE));
        assertThat(date.isAfter(of(2016, JUNE, 22)), is(TRUE));
    }
}
