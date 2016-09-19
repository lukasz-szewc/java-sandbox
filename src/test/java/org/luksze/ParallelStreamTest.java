package org.luksze;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static org.junit.Assert.assertTrue;

public class ParallelStreamTest {

    @Before
    public void setUp() throws Exception {
        configureNumberOfThreads("5");
    }

    @Test
    public void shouldExecuteStreamInMultipleThreads() throws Exception {
        // given
        IntStream parallelStream = IntStream.range(0, 50000).parallel();
        HashSet<Thread> setOfUniqueThreads = new HashSet<>();

        // when
        parallelStream.forEach(integer -> setOfUniqueThreads.add(currentThread()));

        // then
        assertTrue(setOfUniqueThreads.size() > 1);
    }

    private void configureNumberOfThreads(String value) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", value);
    }
}
