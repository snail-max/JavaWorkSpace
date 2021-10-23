package com.wang.servlet.user;


import com.wang.pojo.User;
import com.wang.service.user.UserService;
import com.wang.service.user.UserServiceImpl;
import com.wang.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = null;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取前端传过来的用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassWord = req.getParameter("userPassword");

        // 2、调用业务的从数据库中查出的密码和用户的输入进行对比
        this.userService = new UserServiceImpl();
        User user = userService.login(userCode,userPassWord);

        //3、判断是否为空
        if(user != null){
            //1、将用户的信息存入session
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //跳转到内部主页
            resp.sendRedirect("jsp/frame.jsp");
        }else{//账号和密码不正确
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
