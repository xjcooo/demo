package org.xjc.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjc.demo.exception.PageException;
import org.xjc.demo.utils.ArgsFormatUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * controller增强器
 *
 * @ControllerAdvice、@RestControllerAdvice 区别和@Controller、@RestController的区别一致
 * Created by xjc on 2018-12-7.
 */
@ControllerAdvice
@Aspect
@Component
public class GlobalControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @Pointcut("execution(public * org.xjc.demo.controller.*.*(..))")
    public void controllerPointCut(){

    }

    @Before("controllerPointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        logger.info("[GlobalControllerAdvice] method={}, args={}", joinPoint.getSignature().getName(), ArgsFormatUtil.springMvc(joinPoint));
    }

    /**
     * 统一返回restful格式的异常报文
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object defaultError(HttpServletRequest request, Exception e) {
        Map<String, Object> rs = new HashMap<>();
        rs.put("exception", e.getMessage());
        rs.put("restUrl", request.getRequestURI());
        return rs;
    }

    /**
     * 统一返回错误页面
     *
     * @param e
     * @return
     */
    @ExceptionHandler(PageException.class)
    public ModelAndView defaultErrorPage(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("url", "errorUrl");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
