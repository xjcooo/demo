package org.xjc.demo.es.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by xjc on 2019-3-13.
 */
@RestController
@RequestMapping("gfn")
@Slf4j
public class GrafanaController {

    @RequestMapping(value = "notify", method = RequestMethod.POST)
    public String notify(@RequestBody String body, HttpServletRequest request){
        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            log.info("[key]{}-[value]{}", key, map.get(key));
        }
        Enumeration<String> items = request.getHeaderNames();
        while (items.hasMoreElements()){
            String name = items.nextElement();
            log.info("[header]{}--{}", name, request.getHeader(name));
        }
        log.info("{} login in with {}, [body]{}", "user", "pswd", body);
        return "ok";
    }

}
