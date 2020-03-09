package org.xjc.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xjc.demo.service.HtmlParserService;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by xjc on 2019-2-14.
 */
@Slf4j
@Component
public class HtmlParseServiceImpl implements HtmlParserService {
    /**
     * 获取网页代码
     *
     * @param url
     * @return
     */
    @Override
    public String getHtmlSource(String url) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            URL urls = new URL(url);
            InputStream in =urls.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            String str;
            while ((str = bufr.readLine()) != null) {
                sb.append(str).append("\r\n");
            }
            bufr.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            log.error("源码解析异常", e);
            throw e;
        }
        return sb.toString();
    }

    /**
     * 获取微信头部url
     *
     * @param url
     * @return
     */
    @Override
    public String getWechatTitlePic(String url) {
        try {
            URL urls = new URL(url);
            String str;
            InputStream in =urls.openStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader bufr = new BufferedReader(isr);
            while ((str = bufr.readLine()) != null) {
                if (str.trim().startsWith("var msg_cdn_url = ")) {
                    return str.replace("var msg_cdn_url = ", "")
                            .replace(";", "").replace("\"", "");
                }
            }
            bufr.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            log.error("源码解析异常", e);
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "https://mp.weixin.qq.com/s/N8ApX4M3Bp43Rk_kaKkIyQ";
        String s = new HtmlParseServiceImpl().getWechatTitlePic(url);
        log.info(s);

        System.setProperty("java.awt.headless", "false");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable text = new StringSelection(s);
        clipboard.setContents(text, null);
    }
}
