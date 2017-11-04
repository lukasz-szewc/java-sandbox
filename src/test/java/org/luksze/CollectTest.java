package org.luksze;

import org.junit.Test;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollectTest {

    @Test
    public void stringCollection() throws Exception {
        //given
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        //when
        StringBuilder collect = integerStream.collect(StringBuilder::new, this::appendIntegers, StringBuilder::append);

        //then
        assertEquals("1, 2, 3, 4, 5, 6, ", collect.toString());
    }

    @Test
    public void canCollectToGivenRandomJavaCollection() throws Exception {
        //given
        IntStream range = IntStream.range(0, 5);

        //when
        LinkedHashSet<String> set = range.collect(
                LinkedHashSet::new,
                (strings, value) -> strings.add(valueOf(value)),
                Collection::addAll);

        //then
        assertEquals(5, set.size());
        assertTrue(set.containsAll(asList("0", "1", "2", "3", "4")));
    }

    private void appendIntegers(StringBuilder stringBuilder, Integer integer) {
        stringBuilder.append(integer);
        stringBuilder.append(", ");
    }

}
