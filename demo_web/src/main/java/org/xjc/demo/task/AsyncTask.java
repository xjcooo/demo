package org.xjc.demo.task;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务
 *
 * Created by xjc on 2018-12-11.
 */
@Component
public class AsyncTask {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);
    private static Random random = new Random();

    @Async("taskExecutor")
    public void doOne() throws InterruptedException {
        logger.info("[AsyncTask] doOne start..");
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(random.nextInt(5000));
        stopwatch.stop();
        logger.info("[AsyncTask] doOne spend {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Async("taskExecutor")
    public void doTwo() throws InterruptedException {
        logger.info("[AsyncTask] doTwo start..");
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(random.nextInt(5000));
        stopwatch.stop();
        logger.info("[AsyncTask] doTwo spend {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    @Async("taskExecutor")
    public Future<String> doCallback() throws InterruptedException {
        logger.info("[AsyncTask] doCallback start..");
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(random.nextInt(5000) + 5000);
        stopwatch.stop();
        logger.info("[AsyncTask] doCallback spend {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return new AsyncResult<>("回调成功");
    }

}
