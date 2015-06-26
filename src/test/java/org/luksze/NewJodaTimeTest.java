package org.luksze;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.lang.Boolean.TRUE;
import static java.time.LocalDate.of;
import static java.time.Month.JUNE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NewJodaTimeTest {

    @Test
    public void canCreateLocalDate() throws Exception {
        LocalDate date = constructDate();
        assertThat(date.isLeapYear(), is(TRUE));
        assertThat(date.getMonth(), is(JUNE));
        assertThat(date.isAfter(of(2016, JUNE, 22)), is(TRUE));
    }

    @Test
    public void equalityOfLocalDate() throws Exception {
        LocalDate date = constructDate();
        assertThat(date, equalTo(constructDate()));
        assertThat(date.compareTo(constructDate()), is(0));
    }

    @Test
    public void canCreateLocalTime() throws Exception {
        LocalTime time = LocalTime.of(21, 33, 44, 2);
        assertThat(time.getHour(), is(21));
        assertThat(time.getMinute(), is(33));
        assertThat(time.getSecond(), is(44));
        assertThat(time.getNano(), is(2));
    }

    @Test
    public void canCreateLocalDateTime() throws Exception {
        LocalDateTime localDateTime = constructDate().atTime(21, 33);
        assertThat(localDateTime.getMonth(), is(JUNE));
        assertThat(localDateTime.getHour(), is(21));
    }

    private LocalDate constructDate() {
        return of(2016, JUNE, 23);
    }
}
