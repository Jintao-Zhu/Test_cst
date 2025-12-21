package testthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TestThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        TestThread testThread1 = new TestThread();
        TestThread testThread2 = new TestThread();

        ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(3);

        Future<String> future = executorService.submit(testThread);
        Future<String> future1 = executorService.submit(testThread1);
        Future<String> future2 = executorService.submit(testThread2);

        try {
            System.out.println(future.get());
            System.out.println(future1.get());
            System.out.println(future2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
