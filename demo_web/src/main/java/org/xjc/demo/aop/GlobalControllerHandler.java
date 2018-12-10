package org.xjc.demo.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjc.demo.exception.PageException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjc on 2018-12-7.
 */
@ControllerAdvice
public class GlobalControllerHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object defaultError(HttpServletRequest request, Exception e) {
        Map<String, Object> rs = new HashMap<>();
        rs.put("exception", e.getMessage());
        rs.put("restUrl", request.getRequestURI());
        return rs;
    }

    @ExceptionHandler(PageException.class)
    public ModelAndView defaultErrorPage(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("url", "errorUrl");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
