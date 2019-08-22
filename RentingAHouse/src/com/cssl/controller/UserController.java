package com.cssl.controller;

import com.alibaba.fastjson.JSON;
import com.cssl.error.SysExecption;
import com.cssl.pojo.User;
import com.cssl.service.impl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/userController")
public class UserController {
    private String config = "com/cssl/config/applicationContext.xml";
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext(config);
    UserServiceImpl us = context.getBean(UserServiceImpl.class);

    //默认到登录页面
    @RequestMapping("index")
    public ModelAndView goLogin(ModelAndView mav) {

        mav.setViewName("redirect:page/login.jsp");
//        System.out.println(mav);
        return mav;
    }

    //登录
    @RequestMapping("login")
    public ModelAndView userLogin(ModelAndView mv, String name, String password, HttpSession session) {
        Object o = session.getAttribute("user");
        if(o==null){
            User user = new User(name, password);
            User userLogin = us.userLogin(user);
            if (userLogin != null) {//判断是否登录成功
                session.setAttribute("user", userLogin);
            } else {
                session.setAttribute("code", 404);
                mv.setViewName("login");
            }
        }
        return mv;
    }

    //注册
    @RequestMapping("register")
    public ModelAndView userRegister(ModelAndView mv, User user, HttpSession session) throws SysExecption {
        User use = new User();
        use.setUserName(user.getUserName());
        use.setPassword(user.getPassword());
//        System.out.println("phone::"+user.getTelPhone());
        use.setTelPhone(user.getTelPhone());
        use.setRealName(user.getRealName());
        int i = us.userRegiste(user);
        //判断注册是否成功！
        if (i > 0) {
            session.setAttribute("code", 0);
            mv.setViewName("login");
        } else {
            session.setAttribute("code", 500);
            mv.setViewName("regs");
        }
//        System.out.println("userName:"+user.getUserName());
//        mv.setViewName("login");
        return mv;
    }

    //判断是否有重名
    @RequestMapping(value = "reName")
    public @ResponseBody
    String reName(String name) {
        System.out.println("ajax");
        int i = us.reUserName(name);
        String json = JSON.toJSONString(i);
//        System.out.println(json);
        return json;
    }

    //转发布页面
    @RequestMapping("fabu")
    public String fabu() {
        return "loged/fabu";
    }

    //退出
    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.setAttribute("code", 0);
        session.setAttribute("user",null);
        return "login";
    }

    //跳转注册页面
    @RequestMapping(path = "regs?zas??")
    public String regs() {
        return "regs";
    }


}
