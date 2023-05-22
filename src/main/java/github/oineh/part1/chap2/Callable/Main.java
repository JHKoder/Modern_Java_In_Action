package github.oineh.part1.chap2.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        //before
        Future<String> thread = executorService.submit(new Callable<String>(){
            @Override
            public String call() {
                return Thread.currentThread().getName();
            }
        });

        //after
        Future<String> thread2 = executorService.submit(() -> Thread.currentThread().getName());

    }
}
