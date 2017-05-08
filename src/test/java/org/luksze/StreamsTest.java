package org.luksze;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;

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
}
