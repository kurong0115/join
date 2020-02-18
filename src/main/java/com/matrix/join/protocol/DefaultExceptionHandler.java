package com.matrix.join.protocol;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DefaultExceptionHandler
 * @Description 默认异常处理
 * @Author Administrator
 * @Date 2020/2/1 15:16
 * @Version 1.0
 */
@Component
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    private static Logger logger = LogManager.getLogger(DefaultExceptionHandler.class.getName());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> object = new HashMap<>(16);
        if (ex.getMessage() != null) {
            object.put("code", 500);
            object.put("message", "failed");
            object.put("data", ex.getMessage());
            view.setAttributesMap(object);
            mv.setView(view);
        }
        return mv;
    }
}
