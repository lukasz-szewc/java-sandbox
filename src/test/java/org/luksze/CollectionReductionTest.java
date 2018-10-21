package org.luksze;

import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollectionReductionTest {

    @Test
    public void collectToTailoredObject() {
        //given
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        //when
        TailoredCollection coll = integerStream.collect(TailoredCollection::new,
                TailoredCollection::add, TailoredCollection::addAll);

        //then
        assertEquals("[1, 2, 3, 4, 5, 6]", coll.toString());
    }

    @Test
    public void reduceToTailoredObject() {
        //given
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        //when
        TailoredCollection collection = integerStream.reduce(new TailoredCollection(),
                TailoredCollection::addAndReturn, TailoredCollection::addAllAndReturn);

        //then
        assertEquals("[1, 2, 3, 4, 5, 6]", collection.toString());
    }

    @Test
    public void collectToTailoredObjectLargeStream() {
        //given
        List<Integer> list = IntStream.range(0, 40000).boxed().collect(toList());

        //when
        TailoredCollection collect = list.parallelStream().collect(TailoredCollection::new,
                TailoredCollection::add, TailoredCollection::addAll);

        //then
        assertTrue(collect.containsAll(list));
    }

    @Test
    public void reduceToTailoredObjectLargeStream() {
        //given
        List<Integer> list = IntStream.range(0, 20000).boxed().collect(toList());

        //when
        TailoredCollection collection = list.parallelStream().reduce(new TailoredCollection(),
                TailoredCollection::addAndReturn, TailoredCollection::addAllAndReturn);

        //then
        assertTrue(collection.containsAll(list));
    }
}
