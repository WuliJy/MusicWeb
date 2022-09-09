package com.web.count;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CountServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        int totalCount=1;
        Object count =request.getServletContext().getAttribute("count");
        if (count!=null){
            totalCount=(int)count;
        }
        request.getServletContext().setAttribute("count",totalCount+1);
        response.getWriter().println("网站访问量："+totalCount);
    }
}
