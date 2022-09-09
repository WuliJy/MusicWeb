package com.web.servlet;


import com.pojo.User;
import com.service.UserService;
import com.util.JsonUtils;
import com.util.ResultModel;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserListServlet extends BaseServlet {

    private UserService userService=new UserService();

    public void userList(HttpServletRequest request,HttpServletResponse response){
        System.out.println("--userList执行了--");
        String page=request.getParameter("page");
        String limit=request.getParameter("limit");
        ResultModel model=userService.userList(page,limit);
        JsonUtils.ajax_json(response,model);
    }

    public void updateUser(HttpServletRequest request,HttpServletResponse response){

        try {
            User user = new User();
            BeanUtils.populate(user, request.getParameterMap());
            ResultModel model = userService.updateUser(user);
            JsonUtils.ajax_json(response, model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void delUser(HttpServletRequest request, HttpServletResponse response){
        String userID=request.getParameter("userID");
        ResultModel model=userService.delUser(userID);
        JsonUtils.ajax_json(response,model);
    }

    public void dellAll(HttpServletRequest request, HttpServletResponse response){
        String ids=request.getParameter("ids");
        ResultModel model=userService.dellAll(ids);
        JsonUtils.ajax_json(response,model);
    }
    public void findAll(HttpServletRequest request,HttpServletResponse response){
        ResultModel resultModel=userService.findAllUser();
        JsonUtils.ajax_json(response,resultModel);
    }

    public void findSingle(HttpServletRequest request,HttpServletResponse response){
        String userID=request.getParameter("userID");
        System.out.println("userID="+userID);
        ResultModel resultModel=userService.findUser(userID);
        System.out.println(resultModel.getData());
        JsonUtils.ajax_json(response,resultModel);
    }





}
