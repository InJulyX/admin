package com.admin.config;

import com.admin.utils.SpringUtils;
import com.admin.utils.Threads;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsyncManager {
    private final int DELAYTIME = 10;
    private ScheduledExecutorService executorService = SpringUtils.getBean("scheduledExecutorService");

    private AsyncManager() {
    }

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    public void execute(TimerTask task) {
        executorService.schedule(task, DELAYTIME, TimeUnit.MILLISECONDS);
    }

    public void shutDown() {
        Threads.shutdownAndAwaitTermination(executorService);
    }
}
