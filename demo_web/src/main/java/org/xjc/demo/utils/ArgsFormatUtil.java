package org.xjc.demo.utils;

import com.google.common.base.Joiner;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 参数格式化
 *
 * Created by xjc on 2018-12-11.
 */
public class ArgsFormatUtil {

    private static final Logger logger = LoggerFactory.getLogger(ArgsFormatUtil.class);

    /**
     * 将SpringMVC的controller的入参类型统一打印
     *
     * @param joinPoint
     * @return
     */
    public static String springMvc(JoinPoint joinPoint){
        StringBuilder sb = new StringBuilder("{");
        try {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof ModelAndView){
                    ModelAndView modelAndView = (ModelAndView) arg;
                    sb.append("ModelAndView[viewName#").append(modelAndView.getViewName()).append(",arg#")
                            .append(Joiner.on(",").withKeyValueSeparator("|").join(modelAndView.getModel()))
                    .append("],");
                } else if (arg instanceof Model){
                    Model model = (Model) arg;
                    sb.append("Model[arg#")
                            .append(Joiner.on(",").withKeyValueSeparator("|").join(model.asMap()))
                            .append("],");
                } else if (arg instanceof ModelMap){
                    ModelMap modelMap = (ModelMap) arg;
                    sb.append("ModelMap[arg#")
                            .append(Joiner.on(",").withKeyValueSeparator("|").join(modelMap))
                            .append("],");

                } else if (arg instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) arg;
                    sb.append("HttpServletRequest[arg#")
                            .append(Joiner.on(",").withKeyValueSeparator("|").join(request.getParameterMap()))
                            .append("],");
                } else if (arg instanceof HttpServletResponse) {
                    sb.append("HttpServletRequest[],");
                } else {
                    sb.append(arg).append(",");
                }
            }
        } catch (Exception e){
            logger.error("[ArgsFormatUtil] error,", e);
            return "";
        }
        return sb.substring(0, sb.length() - 1 > 0 ? sb.length() - 1 : sb.length()) + "}";
    }

}
