package org.xjc.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * Created by xjc on 2019-2-14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HtmlParserServiceTest {

    @Autowired
    private HtmlParserService htmlParserService;

    @Test
    public void getWechatPicUrl(){
        String url = "https://mp.weixin.qq.com/s/WSGlUfan9tHPouIvi5pz-A";
        String s = htmlParserService.getWechatTitlePic(url);
        log.info(s);

        System.setProperty("java.awt.headless", "false");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable text = new StringSelection(s);
        clipboard.setContents(text, null);
    }
}
