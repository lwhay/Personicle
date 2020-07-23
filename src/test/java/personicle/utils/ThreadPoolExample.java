package personicle.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {
    private static class SingleRunnable implements Runnable {
        int i;

        @Override public void run() {
            System.out.println("Begin " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End " + i);
        }

        public SingleRunnable(int i) {
            this.i = i;
        }
    }

    public static class Single {
        ThreadPoolExecutor threadPoolExecutor = null;

        public class BlockWhenQueueFull implements RejectedExecutionHandler {

            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                // The pool is full. Wait, then try again.
                try {
                    long waitMs = 250;
                    Thread.sleep(waitMs);
                } catch (InterruptedException interruptedException) {
                }

                executor.execute(r);
            }
        }

        public void transform(int tid) {
            if (threadPoolExecutor == null) {
                BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50, false);

                threadPoolExecutor = new ThreadPoolExecutor(10, 20, 5000, TimeUnit.MILLISECONDS, blockingQueue,
                        //new ExecutorThreadFactory("ThrowableThreadPoolExecutor"), new ThreadPoolExecutor.CallerRunsPolicy());
                        new BlockWhenQueueFull());
            }
            threadPoolExecutor.submit(new SingleRunnable(tid));
        }

        public void shutdown() {
            if (threadPoolExecutor != null) {
                threadPoolExecutor.shutdown();
            }
        }
    }

    public static void main(String[] args) {
        Single single = new Single();
        for (int i = 0; i < 1000; i++) {
            single.transform(i);
        }
        single.shutdown();
    }
}
