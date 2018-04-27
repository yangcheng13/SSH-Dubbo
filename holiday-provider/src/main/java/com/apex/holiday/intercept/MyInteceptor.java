package com.apex.holiday.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @desc: holiday-provider
 * @author: yangcheng
 * @createTime: 2018年4月2日 下午3:37:17
 * @version: v1.0
 */
public class MyInteceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(MyInteceptor.class);
    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub
    

    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2) throws Exception {
        // TODO Auto-generated method stub
        logger.info("controllor Intercept by HandlerInterceptor..."); 

        // check login status here
        return true;
    }

}
