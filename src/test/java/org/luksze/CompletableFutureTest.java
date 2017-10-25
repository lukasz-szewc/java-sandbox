package org.luksze;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CompletableFutureTest {

    @Test
    public void simpleExampleOfCompletableFuture() throws Exception {
        //given
        CompletableFuture<String> stringCompletableFuture = new CompletableFuture<>();

        //when
        threadThatEventuallyCompletesFuture(stringCompletableFuture);

        //then
        assertThat(stringCompletableFuture.get(), is("OK"));

    }

    @Test
    public void runAsyncFutureTest() throws Exception {
        //given
        CompletableFuture<Integer> integerCompletableFuture = supplyAsync(() -> 88);

        //when
        Integer result = integerCompletableFuture.get();

        //then
        assertThat(result, is(88));
    }

    @Test
    public void supplyAsyncExecutedByOneExecutor() throws Exception {
        //given
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //when
        List<CompletableFuture<String>> futures = IntStream.range(0, 100)
                .mapToObj(value -> supplyAsync(() -> Thread.currentThread().getName(), executor))
                .collect(toList());

        //then
        assertThat(futures, new ExecutedByAtLeastTwoDistinctThreads());
    }

    private void threadThatEventuallyCompletesFuture(CompletableFuture<String> stringCompletableFuture) {
        new Thread(new MyRunnable(stringCompletableFuture)).start();
    }

    private static class MyRunnable implements Runnable {
        private final CompletableFuture<String> future;

        MyRunnable(CompletableFuture<String> completableFuture) {
            this.future = completableFuture;
        }

        @Override
        public void run() {
            simulateLongComputation();
            future.complete("OK");
        }

        private void simulateLongComputation() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

    private static class ExecutedByAtLeastTwoDistinctThreads extends TypeSafeMatcher<List<CompletableFuture<String>>> {

        @Override
        protected boolean matchesSafely(List<CompletableFuture<String>> items) {
            return items.stream().map(CompletableFutureTest::get).distinct().collect(toList()).size() == 2;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("Should be executed by at least two distinct threads");
        }
    }

    private static String get(CompletableFuture<String> completableFuture) {
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException();
        }
    }

}
