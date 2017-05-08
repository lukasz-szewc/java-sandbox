package org.luksze;

import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.lang.Character.valueOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StreamsTest {

    @Test
    public void simpleStreamTest() throws Exception {
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
    public void orderOfInvocationsMatter() throws Exception {
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
}
