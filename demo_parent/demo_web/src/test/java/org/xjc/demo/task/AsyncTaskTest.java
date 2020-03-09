package org.xjc.demo.task;

import com.google.common.base.Stopwatch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by xjc on 2018-12-11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTaskTest {

    @Autowired
    private AsyncTask asyncTask;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void doOne() {
    }

    @Test
    public void doTwo() {
    }

    @Test
    public void testAsyncTask() throws InterruptedException, ExecutionException {
        Stopwatch stopwatch = Stopwatch.createStarted();

        asyncTask.doOne();
        asyncTask.doTwo();
        Future<String> callbackRs = asyncTask.doCallback();
        while (true) {
            if (callbackRs.isDone()) {
                System.out.println(callbackRs.get());
                break;
            }
        }
        long time = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);

        System.out.println("任务耗时：" + time + "ms");
    }
}
