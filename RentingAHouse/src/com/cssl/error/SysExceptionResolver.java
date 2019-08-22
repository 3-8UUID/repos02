package com.cssl.error;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//全局异常处理器
public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        SysExecption sysExecption = null;
        if(e instanceof SysExecption){
            sysExecption = (SysExecption) e;
        }else {
            sysExecption = new SysExecption("未知异常！！");

        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
//        mv.addObject("message",sysExecption.getMessage());
        return mv;
    }
}
