package org.luksze;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}
