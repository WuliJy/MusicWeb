package com.web.servlet;

import com.pojo.Admin;
import com.service.AdminService;
import com.service.IndexService;
import com.util.JsonUtils;
import com.util.ResultModel;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/index")
public class IndexServlet extends BaseServlet {
    private IndexService indexService=new IndexService();
    public void singer_lists(HttpServletRequest request,HttpServletResponse response){
        String page=request.getParameter("page");
        String limit=request.getParameter("limit");
        System.out.println("-------------"+page+"----------"+limit);
        ResultModel resultModel=indexService.singerListByFenYe(page,limit);
        JsonUtils.ajax_json(response,resultModel);
    }

    public void singerDetail(HttpServletRequest request,HttpServletResponse response){
        String singerId=request.getParameter("singerId");
        ResultModel resultModel=indexService.findSingerDetail(singerId);
        JsonUtils.ajax_json(response,resultModel);
    }

}
