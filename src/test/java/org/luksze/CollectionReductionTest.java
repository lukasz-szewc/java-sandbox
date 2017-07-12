package org.luksze;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollectionReductionTest {

    @Test
    public void collectToTailoredObject() throws Exception {
        //given
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        //when
        TailoredCollection coll = integerStream.collect(TailoredCollection::new,
                TailoredCollection::add, TailoredCollection::addAll);

        //then
        assertEquals(coll.toString(), "[1, 2, 3, 4, 5, 6]");
    }

    @Test
    public void reduceToTailoredObject() throws Exception {
        //given
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        //when
        TailoredCollection collection = integerStream.reduce(new TailoredCollection(),
                TailoredCollection::addAndReturn, TailoredCollection::addAllAndReturn);

        //then
        assertEquals(collection.toString(), "[1, 2, 3, 4, 5, 6]");
    }

    @Test
    public void collectToTailoredObjectLargeStream() throws Exception {
        //given
        List<Integer> list = IntStream.range(0, 40000).boxed().collect(toList());

        //when
        TailoredCollection collect = list.parallelStream().collect(TailoredCollection::new,
                TailoredCollection::add, TailoredCollection::addAll);

        //then
        assertTrue(collect.containsAll(list));
    }

    @Test
    public void reduceToTailoredObjectLargeStream() throws Exception {
        //given
        List<Integer> list = IntStream.range(0, 20000).boxed().collect(toList());

        //when
        TailoredCollection collection = list.parallelStream().reduce(new TailoredCollection(),
                TailoredCollection::addAndReturn, TailoredCollection::addAllAndReturn);

        //then
        assertTrue(collection.containsAll(list));
    }
}
