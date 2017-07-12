package org.luksze;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

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

}
