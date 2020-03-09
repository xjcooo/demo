package org.xjc.demo.freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjc on 2018-12-20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestUtil {

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;
    @Autowired
    private Configuration freemarkerConfiguration;

    @Test
    public void testFreemarker() throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("username", "xjcooo");

        String rs = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("mailTemplate.ftl"), model);

        log.info("\n{}", rs);
    }

}
