package org.xjc.demo.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 网页解析
 *
 * Created by xjc on 2019-2-14.
 */
@Service
public interface HtmlParserService {

    /**
     * 获取网页代码
     *
     * @param url
     * @return
     */
    String getHtmlSource(String url) throws IOException;

    /**
     * 获取微信头部url
     * @param url
     * @return
     */
    String getWechatTitlePic(String url);
}
