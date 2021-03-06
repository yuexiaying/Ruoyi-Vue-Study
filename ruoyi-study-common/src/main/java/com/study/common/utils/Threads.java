package com.study.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程相关工具类
 *
 * @author fjding
 * @date 2021/9/11
 */
public class Threads {
    private static final Logger logger = LoggerFactory.getLogger(Threads.class);

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止线程池
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍人超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     *
     * @param pool
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                        logger.info("Pool did not terminate");
                    }
                }
            } catch (InterruptedException e) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void printException(Runnable r, Throwable t) {
        // 获得Future中的异常
        if (t == null && r instanceof Future<?>) {
            Future<?> future = (Future<?>) r;
            if (future.isDone()) {
                try {
                    future.get();
                    // 注意几个异常的处理方式
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    t = e.getCause();
                } catch (CancellationException e) {
                    t = e;
                }
            }
        }
        // 打印异常
        if (t != null) {
            logger.error(t.getMessage(), t);
        }
    }
}
