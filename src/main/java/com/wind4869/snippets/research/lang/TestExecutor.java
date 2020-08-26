package com.wind4869.snippets.research.lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TestExecutor
 *
 * @author wind4869
 * @since 1.0.0
 */
public class TestExecutor {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Test starts");

        ExecutorService pool = new ThreadPoolExecutor(
                5, 5,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000)
        );

        for (int i = 0; i < 10; i++) {
            final int index = i + 1;
            pool.submit(() -> {
                try {
                    Thread.sleep(5_000);
                    System.out.println("Task #" + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool.shutdown();
        if (!pool.isTerminated()) {
            boolean success = pool.awaitTermination(15, TimeUnit.SECONDS);
            System.out.println("Wait tasks to finish: " + success);
        }

        System.out.println("Test ends");
    }
}
