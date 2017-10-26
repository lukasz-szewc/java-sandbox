package org.luksze;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ReduceTest {

    @Test
    public void simpleSumReduction() throws Exception {
        //given
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        //when
        Optional<Integer> reduce = integerStream.reduce((Integer first, Integer second) -> first + second);

        //then
        assertTrue(reduce.isPresent());
        assertEquals(reduce.get(), Integer.valueOf(21));
    }

    @Test
    public void emptyStreamWillReturnEmptyOptional() throws Exception {
        //given
        Stream<Integer> integerStream = Stream.empty();

        //when
        Optional<Integer> reduce = integerStream.reduce((Integer first, Integer second) -> first + second);

        //then
        assertFalse(reduce.isPresent());
    }

    @Test
    public void reductionToOptionalThatWillBePresent() throws Exception {
        //given
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        //when
        Optional<Integer> reduce = integerStream.reduce((Integer first, Integer second) -> first + second);

        //then
        assertTrue(reduce.isPresent());
        assertEquals(reduce.get(), Integer.valueOf(21));
    }

    @Test
    public void regularReduction() throws Exception {
        //given
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        //when
        Integer reduced = integerStream.reduce(0, (Integer first, Integer second) -> first + second);

        //then
        assertEquals(reduced, Integer.valueOf(21));
    }

    @Test
    public void regularReductionOnEmptyStreamWillProduceZero() throws Exception {
        //given
        Stream<Integer> integerStream = Stream.empty();

        //when
        Integer reduced = integerStream.reduce(0, (Integer first, Integer second) -> first + second);

        //then
        assertEquals(reduced, Integer.valueOf(0));
    }
}
