package org.xjc.demo.rabbitmq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xjc.demo.rabbitmq.provider.Queuer;

/**
 * Created by xjc on 2018-11-6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueTest {

    @Autowired
    private Queuer queuer;

    @Test
    public void testSendMessage() {
        String[] s = {"123", "1234", "hello"};
        for (int i = 0; i < 10; i++) {
            queuer.sendMessage(s[i%s.length]+","+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
