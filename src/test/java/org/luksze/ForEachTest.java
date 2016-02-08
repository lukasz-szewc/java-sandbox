package org.luksze;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForEachTest {
    @Test
    public void simpleForEachOverListTest() throws Exception {
        //given
        List<Integer> sourceList = Arrays.asList(1, 2, 3);
        List<Integer> destinationList = new ArrayList<>();

        //when
        sourceList.forEach(integer -> destinationList.add(integer));

        //then
        Assert.assertTrue(sourceList.containsAll(destinationList));
        Assert.assertTrue(destinationList.containsAll(sourceList));

    }
}
