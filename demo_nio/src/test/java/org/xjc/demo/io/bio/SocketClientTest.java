package org.xjc.demo.io.bio;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * SocketClient测试类
 *
 * Created by xjc on 2019-1-16.
 */
@Slf4j
public class SocketClientTest {

    SocketClient client;

    @Before
    public void init() throws IOException {
        try {
            client = new SocketClient();
        } catch (IOException e) {
            log.error("create SocketClient error", e);
            throw e;
        }
    }

    @Test
    public void sendMessage() {
        client.sendMessage("login:xjc");
    }

    @After
    public void destroy(){
        if (client != null){
            try {
                client.close();
            } catch (IOException e) {

            }
        }
    }
}
