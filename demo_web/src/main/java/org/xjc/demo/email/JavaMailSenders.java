package org.xjc.demo.email;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjc on 2018-12-12.
 */
@Component
public class JavaMailSenders {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    private Template template;

    @PostConstruct
    public void init() {
        try {
            template = freeMarkerConfig.getConfiguration().getTemplate("mailTemplate.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 简单邮件
     *
     * @throws Exception
     */
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xujiancheng@touker.com");
        message.setTo("xujiancheng@touker.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

    /**
     * 有附件的邮件
     *
     * @throws Exception
     */
    public void sendAttachmentsMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("xujiancheng@touker.com");
        helper.setTo("xujiancheng@touker.com");
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\wangchh\\Desktop\\ideaFast.jpg"));
        FileSystemResource file2 = new FileSystemResource(new File("C:\\Users\\wangchh\\Desktop\\aimind.png"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file2);

        mailSender.send(mimeMessage);

    }

    /**
     * 嵌入静态资源的邮件
     * <p>
     * 需要注意的是addInline函数中资源名称weixin需要与正文中cid:weixin对应起来
     *
     * @throws Exception
     */
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("xujiancheng@touker.com");
        helper.setTo("xujiancheng@touker.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\wangchh\\Desktop\\ideaFast.jpg"));
        helper.addInline("weixin", file);

        mailSender.send(mimeMessage);

    }

    /**
     * 模板定义的邮件内容
     *
     * @throws Exception
     */
    public void sendTemplateMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("xujiancheng@touker.com");
        helper.setTo("xujiancheng@touker.com");
        helper.setSubject("主题：模板邮件");

        Map<String, Object> model = new HashMap<>();
        model.put("username", "xjcooo");

        StringWriter stringWriter = new StringWriter();
        template.process(model, new BufferedWriter(stringWriter));
        String text = stringWriter.toString();
        stringWriter.flush();
        stringWriter.close();
        helper.setText(text, true);

        mailSender.send(mimeMessage);
    }
}
