package com.web.servlet;

import com.pojo.User;
import com.service.UserService;
import com.util.FileUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/RegisterServlet") //设置请求路径， 重要，非常重要
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            //2.表单的其他值
            String userPwd=request.getParameter("userPwd");
            System.out.println(userPwd);
            User user=new User();
            BeanUtils.populate(user,request.getParameterMap());
            //7.调用service层中方法
            System.out.println(user);
            UserService userService=new UserService();
            boolean flag=userService.register(user);
//            Cookie cookie=new Cookie("userPhone",userPhone);
//            response.addCookie(cookie);
            if(flag){
                //注册成功跳转登录页面
                response.sendRedirect("welcome.html");
            }else{
                response.sendRedirect("defeated.html");
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
