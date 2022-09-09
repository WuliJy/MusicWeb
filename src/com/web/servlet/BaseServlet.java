package com.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //设置编码格式，那么继承这个类的Servlet就不用再设置编码了
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            //获取页面的请求方法
            String methodName=request.getParameter("method");
            //利用反射找到这个方法  this代表你请求的servlet
            Class c=this.getClass();
            Method method = c.getMethod(methodName, HttpServletRequest.class,
                    HttpServletResponse.class);
            //执行这个方法,obj是指你的方法的返回的结果
            Object obj=method.invoke(this,request,response);
            //约定优于配置，你自己写的servlet的方法的返回值类型
            //只有2种选中  void 和 String
            // void ajax请求，不跳转页面,使用PrintWriter方式响应客户端
            // String 需要跳转页面，跳转分为 转发"页面路径"  和  重定向"redirect:页面路径"
            if(Objects.nonNull(obj)){
                String path=obj.toString().trim();
                if(path.startsWith("redirect:")){ //重定向
                    // redirect:heroInfo.html
                    response.sendRedirect(path.substring("redirect:".length()));
                }else{
                    // heroInfo.html
                    request.getRequestDispatcher(path).forward(request,response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
