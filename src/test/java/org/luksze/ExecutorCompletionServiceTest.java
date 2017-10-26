package org.luksze;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;

public class ExecutorCompletionServiceTest {

    private ExecutorService executorService;

    @Before
    public void setUp() throws Exception {
        executorService = Executors.newFixedThreadPool(3);
    }

    @After
    public void tearDown() throws Exception {
        executorService.shutdown();
    }

    @Test
    public void happyPathScenarioTest() throws Exception {
        //given
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        //when
        completionService.submit(new SampleCallable(600));
        completionService.submit(new SampleCallable(400));
        completionService.submit(new SampleCallable(200));

        //then
        assumeThat(completionService.take().get(), is(200));
        assumeThat(completionService.take().get(), is(400));
        assumeThat(completionService.take().get(), is(600));
    }

    private static class SampleCallable implements Callable<Integer> {

        private final int millis;

        SampleCallable(int millis) {
            this.millis = millis;
        }

        @Override
        public Integer call() throws Exception {
            Thread.sleep(millis);
            return millis;
        }
    }
}
