package org.xjc.demo.aop;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjc on 2018-12-7.
 */
@RestControllerAdvice
public class GlobalControllerHandler {

    @ExceptionHandler(Exception.class)
    public Object defaultError(HttpServletRequest request, Exception e){
        Map<String, Object> rs = new HashMap<>();
        rs.put("exception", e.getMessage());
        rs.put("restUrl", request.getRequestURI());
        return rs;
    }

}
