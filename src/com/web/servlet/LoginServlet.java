package com.web.servlet;


import com.pojo.User;
import com.service.UserService;
import com.util.JsonUtils;
import com.util.ResultModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
    private UserService userService = new UserService();
    public void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userPhone = request.getParameter("userPhone");
        String userPwd = request.getParameter("userPwd");
        System.out.println(userPhone);
        System.out.println(userPwd);
        ResultModel resultModel = userService.login(userPhone, userPwd);
        HttpSession session = request.getSession();
        if (resultModel.getData() != null) {
            session.setAttribute("user", (User) resultModel.getData());
        }
        JsonUtils.ajax_json(response,resultModel);
    }
}