package org.xjc.demo.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by xjc on 2018-12-12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaMailSendersTest {

    @Autowired
    private JavaMailSenders javaMailSenders;

    @Test
    public void sendSimpleMail() {
        try {
            javaMailSenders.sendSimpleMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendAttachmentsMail() {
        try {
            javaMailSenders.sendAttachmentsMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendInlineMail() {
        try {
            javaMailSenders.sendInlineMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendTemplateMail() {
        try {
            javaMailSenders.sendTemplateMail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
