package com.cssl.servlet;

import com.alibaba.fastjson.JSON;
import com.cssl.error.SysExecption;
import com.cssl.pojo.User;
import com.cssl.service.impl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    private ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("com/cssl/config/applicationContext.xml");
    private UserServiceImpl us = context.getBean(UserServiceImpl.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=utf-8");
//        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        System.out.println("action:::" + action);
//        if ("login".equals(action)) {
//            //登录
//            userLogin(request, response);
//        } else if ("regs".equals(action)) {
//            //注册
//            userRegs(request, response);
//        } else if ("index".equals(action)) {
//            request.getSession().removeAttribute("code");
//            response.sendRedirect("page/login.jsp");
//        } else if ("rename".equals(action)) {
//            //检查是否重名
//            regUserName(request, response);
//        }
    }

    private void regUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int i = us.reUserName(name);
        PrintWriter print = response.getWriter();
        String json = JSON.toJSONString(i);
        print.write(json);
        print.flush();
        print.close();
    }

    private void userRegs(HttpServletRequest request, HttpServletResponse response) throws IOException, SysExecption {
//        UserServiceImpl us = new UserServiceImpl();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String telephone = request.getParameter("telephone");
        String username = request.getParameter("username");
        if (!password.equals(repassword)) {
            request.getSession().setAttribute("code", 500);
            response.sendRedirect("page/regs.jsp");
        }
        User user = new User();
        user.setUserName(name);
        user.setPassword(repassword);
        user.setTelPhone(telephone);
        user.setRealName(username);
        int i = us.userRegiste(user);
        //判断注册是否成功！
        if (i > 0) {
            response.sendRedirect("page/login.jsp");
        } else {
            request.getSession().setAttribute("code", 500);
            response.sendRedirect("page/regs.jsp");
        }
    }

    private void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        UserServiceImpl us = new UserServiceImpl();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User(name, password);
        User userLogin = us.userLogin(user);
        if (userLogin != null) {//判断是否登录成功
            request.getSession().setAttribute("user", userLogin);
            int id = userLogin.getId();
            int admin = us.isAdmin(id);
            if (admin == 1) {//判断是否为管理员
                response.sendRedirect("page/guanli.jsp");
            } else {
                response.sendRedirect("page/list.jsp");
            }
        } else {
            request.getSession().setAttribute("code", 404);
            response.sendRedirect("page/login.jsp");
        }
    }
}
