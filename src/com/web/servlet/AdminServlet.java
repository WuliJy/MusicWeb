package com.web.servlet;

import com.pojo.Admin;
import com.service.AdminService;
import com.util.JsonUtils;
import com.util.ResultModel;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/admin")
public class AdminServlet extends BaseServlet {
    private AdminService adminService = new AdminService();
    public void adminLogin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("---------adminLogin-----------");
        try {
            //1.设置编码格式，因为在BaseServlet中设置好了
            //2.获取页面传递的用户名和密码
            Admin admin = new Admin();
            BeanUtils.populate(admin, request.getParameterMap());
            System.out.println("--测试admin是否有值:--" + admin);
            //3.调用service
            ResultModel resultModel= adminService.adminLogin(admin);
            if(resultModel.getCode()==1){
                //登录成功，一定要将成功的管理员用户存入到session
                //登录拦截的时候使用
                HttpSession session=request.getSession();
                session.setAttribute("admin",(Admin)resultModel.getData());
            }
            System.out.println(resultModel);
            //4.用封装好的JsonUtils写给页面
            JsonUtils.ajax_json(response,resultModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String loginout(HttpServletRequest request,HttpServletResponse response){
        System.out.println("-------管理员用户退出---------");
        HttpSession session=request.getSession();
        session.removeAttribute("admin");
        session.invalidate();
        session=null;
        return "redirect:/music/adminLogin.html";
    }

}
