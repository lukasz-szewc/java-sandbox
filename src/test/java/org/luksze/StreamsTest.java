package org.luksze;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Character.valueOf;
import static java.lang.Integer.valueOf;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

public class StreamsTest {

    @Test
    public void simpleStreamTest() {
        //given
        Stream<Character> stream = Stream.of('e', 'c', 'x', 'k', 'a', 'h');

        //when
        Character[] sortedCharsLessThenG = stream
                .filter(character -> character.compareTo('g') < 0)
                .sorted()
                .toArray(Character[]::new);

        //then
        assertArrayEquals(sortedCharsLessThenG, new Character[]{'a', 'c', 'e'});
    }

    @Test
    public void assertHorizontalOrderOfInvocationInsideStream() {
        //given
        LinkedList<String> invocationList = new LinkedList<>();

        //when
        Stream.of('a', 'b').map(character -> {
            String string = character.toString();
            invocationList.addLast(string);
            return string;
        }).filter(string -> {
            invocationList.addLast(string);
            return true;
        }).toArray(String[]::new);

        //then
        assertEquals(invocationList.size(), 4);
        assertEquals(invocationList.pollFirst(), "a");
        assertEquals(invocationList.pollFirst(), "a");
        assertEquals(invocationList.pollFirst(), "b");
        assertEquals(invocationList.pollFirst(), "b");
        assertTrue(invocationList.isEmpty());
    }

    @Test
    public void orderOfInvocationsMatter() {
        //given
        LinkedList<Character> invocationList = new LinkedList<>();

        //when
        Stream.of('a', 'b').filter(character -> {
            invocationList.addLast(character);
            return character != 'a';
        }).map(character -> {
            invocationList.addLast(character);
            return character.toString();
        }).toArray(String[]::new);

        //then
        assertEquals(invocationList.size(), 3);
        assertEquals(invocationList.pollFirst(), valueOf('a'));
        assertEquals(invocationList.pollFirst(), valueOf('b'));
        assertEquals(invocationList.pollFirst(), valueOf('b'));
        assertTrue(invocationList.isEmpty());
    }

    @Test
    public void simpleSumReductionTest() throws Exception {
        //given
        Stream<Integer> integerStream = Stream.of(1, 4, 6, 7, 9);

        //when
        Integer sum = integerStream.reduce(0, (first, second) -> first + second);

        //then
        assertEquals(sum, valueOf(27));
    }

    @Test
    public void infiniteStreamExample() {
        //given
        Stream<YearOfLife> stream = Stream.iterate(new YearOfLife(1), YearOfLife::nextOne);

        //when
        List<YearOfLife> list = stream.limit(4).collect(toList());

        //then
        assertEquals(list.size(), 4);
        assertThat(list, hasItem(new YearOfLife(1)));
        assertThat(list, hasItem(new YearOfLife(2)));
        assertThat(list, hasItem(new YearOfLife(3)));
        assertThat(list, hasItem(new YearOfLife(4)));
    }

    @Test
    public void randomNumbersCanBeGeneratedAsStream() {
        //given
        IntStream ints = new Random().ints(0, 30);
        AtomicInteger counter = new AtomicInteger();

        //when
        boolean foundFlag = ints.anyMatch(value -> {
            counter.incrementAndGet();
            return value == 15;
        });

        //then
        assertTrue(foundFlag);
        assertTrue(counter.get() >= 1 && counter.get() < 10000);
    }
}
