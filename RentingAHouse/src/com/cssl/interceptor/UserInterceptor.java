package com.cssl.interceptor;

import com.cssl.pojo.User;
import com.cssl.service.impl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        System.out.println("预处理方法");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView mv) throws Exception {
        //判断是否管理员
        String config = "com/cssl/config/applicationContext.xml";
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(config);
        UserServiceImpl us = context.getBean(UserServiceImpl.class);
        HttpSession session = request.getSession();
        User userAdmin = (User) session.getAttribute("user");//获取登录对象的全部信息
        if (userAdmin!=null){
            int isAdmin = us.isAdmin(userAdmin.getId());//根据是否是管理员跳转至不同的页面
            //通过ModelAndView转到需要的视图
            String url = isAdmin > 0 ? "loged/guanli" : "loged/list";
            mv.setViewName(url);
        }
//        System.out.println("即将进入的画面："+mv.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
