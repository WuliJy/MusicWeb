package com.web.servlet;


import com.pojo.Singer;
import com.service.SingerService;
import com.util.JsonUtils;
import com.util.ResultModel;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/singer")
public class SingerServlet extends BaseServlet {
    private SingerService singerService=new SingerService();

    public void findAll(HttpServletRequest request,HttpServletResponse response){
        ResultModel resultModel=singerService.findAllSinger();
        JsonUtils.ajax_json(response,resultModel);
    }
    public void dellAll(HttpServletRequest request, HttpServletResponse response){
        String ids=request.getParameter("ids");
        ResultModel model=singerService.dellAll(ids);
        JsonUtils.ajax_json(response,model);
    }
    public void updateSinger(HttpServletRequest request,HttpServletResponse response){
        try {
            Singer singer = new Singer();
            BeanUtils.populate(singer, request.getParameterMap());
            ResultModel model = singerService.updateSinger(singer);
            JsonUtils.ajax_json(response, model);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
//    public void findSingle(HttpServletRequest request,HttpServletResponse response){
//        String singerId=request.getParameter("singerId");
//        System.out.println("singerId="+singerId);
//        ResultModel resultModel=singerService.findSinger(singerId);
//        System.out.println(resultModel.getData());
//        JsonUtils.ajax_json(response,resultModel);
//    }



    public void addSinger(HttpServletRequest request, HttpServletResponse response){
        try {
            System.out.println("--------addSinger执行了-------------");
            Singer singer=new Singer();
            Map<String,String[]> map=request.getParameterMap();
            BeanUtils.populate(singer,map);
            System.out.println("检测singer的值="+singer);
            ResultModel model =singerService.addSinger(singer,map.get("singerImgs")[0]);
            JsonUtils.ajax_json(response,model);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ajax请求
    // 1设置编码格式  2获取值   3调用service方法  4ajax请求将值使用流传递给页面
    public  void singerList(HttpServletRequest request,HttpServletResponse response){
        System.out.println("----------singerList执行了-------------");
//        接口地址。默认会自动传递两个参数：
//         ?page=1&limit=30（该参数可通过 request 自定义）
//        page 代表当前页码、limit 代表每页数据量
        String pageNum=request.getParameter("page"); //当前页码
        String pageSize=request.getParameter("limit"); //每页显示2条
        System.out.println(pageNum+","+pageSize);
        ResultModel resultModel=
                singerService.singerListByFenYe(pageNum,pageSize);
        JsonUtils.ajax_json(response,resultModel);
    }


    public void delSinger(HttpServletRequest request,HttpServletResponse response){
        System.out.println("----------delSinger执行了------------------");
        String singerId=request.getParameter("singerId");
        System.out.println("要删除的是："+singerId);
        ResultModel model=singerService.deleteSinger(singerId);
        JsonUtils.ajax_json(response,model);
    }

    public void findSingerById(HttpServletRequest request,HttpServletResponse response){
        System.out.println("----------findSingerById执行了------------------");
        String singerId=request.getParameter("singerId");
        System.out.println("后台接收到你要搜索:"+singerId);
        //去HeroService中定义一个方法，用于查询英雄信息，并且要把的图片一并查出来
        ResultModel model=singerService.selectSingerAndImg(singerId);
        JsonUtils.ajax_json(response,model);
    }
}
