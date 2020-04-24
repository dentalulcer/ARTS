package com.dbdou.blog.concurrency.executor;

import cn.hutool.core.collection.ConcurrentHashSet;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dentalulcer
 */
@Slf4j
public class CustomThreadPool {

    private final ReentrantLock lock = new ReentrantLock();

    private volatile int coreSize;

    private volatile int maxSize;

    private long keepAliveTime;

    private TimeUnit unit;

    private BlockingQueue<Runnable> workQueue;

    /** 存放线程池 */
    private volatile Set<Worker> workers;

    /** 是否关闭线程池标志 */
    private AtomicBoolean isShutdown = new AtomicBoolean(false);

    private AtomicInteger totalTask = new AtomicInteger();

    private Object shutdownNotify = new Object();

    private Notify notify;

    public CustomThreadPool(int coreSize, int maxSize, long keepAliveTime,
                            TimeUnit unit, BlockingQueue<Runnable> workQueue, Notify notify) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = unit;
        this.workQueue = workQueue;
        this.notify = notify;

        workers = new ConcurrentHashSet<>();
    }

    public <T> Future<T> submit(Callable<T> callable) {
        FutureTask<T> future = new FutureTask(callable);
        execute(future);
        return future;
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("runnable npe");
        }
        if (isShutdown.get()) {
            log.info("线程池已关闭，不能再提交任务！");
            return ;
        }

        totalTask.incrementAndGet();

        if (workers.size() < coreSize) {
            addWorker(runnable);
            return ;
        }

        boolean offer = workQueue.offer(runnable);
        // 写入队列失败
        if (!offer) {
            if (workers.size() < maxSize) {
                // 创建新的线程执行
                addWorker(runnable);
                return ;
            } else {
                log.error("超过最大线程数");
                try {
                    workQueue.put(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addWorker(Runnable runnable) {
        Worker worker = new Worker(runnable, true);
        worker.startTask();
        workers.add(worker);
    }

    private final class Worker extends Thread {

        private Runnable task;

        private Thread thread;

        private boolean isNewTask;

        public Worker(Runnable task, boolean isNewTask) {
            this.task = task;
            this.isNewTask = isNewTask;
            thread = this;
        }

        public void startTask() {
            thread.start();
        }

        public void close() {
            thread.interrupt();
        }

        @Override
        public void run() {
            Runnable task = null;

            if (isNewTask) {
                task = this.task;
            }

            boolean compile = true;

            try {
                while ((task != null || (task = getTask()) != null)) {
                    try {
                        // 执行任务
                        task.run();
                    } catch (Exception e) {
                        compile = false;
                        throw e;
                    } finally {
                        // 任务执行完毕
                        task = null;
                        int number = totalTask.decrementAndGet();
                        if (number == 0) {
                            synchronized (shutdownNotify) {
                                shutdownNotify.notify();
                            }
                        }
                    }
                }
            } finally {
                boolean remove = workers.remove(this);
                if (!compile) {
                    addWorker(null);
                }
                tryClose(true);
            }

        }
    }

    private Runnable getTask() {
        if (isShutdown.get() && totalTask.get() == 0) {
            return null;
        }

        lock.lock();

        try {
            Runnable task = null;
            if (workers.size() > coreSize) {
                //大于核心线程数时需要用保活时间获取任务
                task = workQueue.poll(keepAliveTime, unit);
            } else {
                task = workQueue.take();
            }

            if (task != null) {
                return task;
            }
        } catch (InterruptedException e) {
            return null;
        } finally {
            lock.unlock();
        }

        return null;
    }


    public void shutdown() {
        isShutdown.set(true);
        tryClose(true);
    }

    /**
     * 立即关闭线程池，会造成任务丢失
     */
    public void shutDownNow() {
        isShutdown.set(true);
        tryClose(false);

    }

    /**
     * 阻塞等到任务执行完毕
     */
    public void mainNotify() {
        synchronized (shutdownNotify) {
            while (totalTask.get() > 0) {
                try {
                    shutdownNotify.wait();
                    if (notify != null) {
                        notify.notifyListen();
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    /**
     * 关闭线程池
     *
     * @param isTry true 尝试关闭      --> 会等待所有任务执行完毕
     *              false 立即关闭线程池--> 任务有丢失的可能
     */
    private void tryClose(boolean isTry) {
        if (!isTry) {
            closeAllTask();
        } else {
            if (isShutdown.get() && totalTask.get() == 0) {
                closeAllTask();
            }
        }

    }

    /**
     * 关闭所有任务
     */
    private void closeAllTask() {
        for (Worker worker : workers) {
            //LOGGER.info("开始关闭");
            worker.close();
        }
    }

    /**
     * 获取工作线程数量
     *
     * @return
     */
    public int getWorkerCount() {
        return workers.size();
    }

}
