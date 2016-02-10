package org.luksze;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ForEachTest {
    @Test
    public void simpleForEachOverListTest() throws Exception {
        //given
        List<Integer> sourceList = asList(1, 2, 3);
        List<Integer> destinationList = new ArrayList<>();

        //when
        sourceList.forEach(integer -> destinationList.add(integer * 2));

        //then
        assertTrue(destinationList.contains(2));
        assertTrue(destinationList.contains(4));
        assertTrue(destinationList.contains(6));

    }

    @Test
    public void simpleForEachOverMapTest() throws Exception {
        //given
        Map<String, Integer> map = mapWithThreeKeys();
        AtomicInteger amount = new AtomicInteger();

        //when
        map.forEach((s, integer) -> amount.addAndGet(integer));

        //then
        assertEquals(6, amount.get());

    }

    private Map<String, Integer> mapWithThreeKeys() {
        Map<String, Integer> map = new HashMap<>();
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        return map;
    }
}
