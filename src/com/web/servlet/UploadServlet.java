package com.web.servlet;

import com.util.JsonUtils;
import com.util.ResultModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet("/UploadServlet")
@MultipartConfig  //重要，非常重要
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String basePath = "D:\\upload_temp";
        Collection<Part> parts = request.getParts(); //一个Part就一个图片
        if(Objects.nonNull(parts)){
            for (Part part : parts) {
                //判断上传的是否是文件
                if(part.getSize() > 0){
                    //获取上传的文件名称  dog1.jpg
                    String fname = part.getSubmittedFileName();
                    //随机产生一个uuid作为文件名称   分布式id生成技术-雪花算法（twitter）
                    String uuid = UUID.randomUUID().toString();
                    //获取文件的后缀名
                    String suffix = fname.substring(fname.lastIndexOf("."));
                    //组合uuid和文件后缀成为新的文件名称(包含".")
                    fname = uuid + suffix;
                    //将客户端上传的文件写入到服务器指定文件中
                    part.write(basePath + File.separator + fname);

                    ResultModel resultModel=new ResultModel();
                    resultModel.setCode(1); //图片上传成功
                    Map<String,String> map=new HashMap<>();
                    map.put("src",fname);
                    resultModel.setData(map);
                    JsonUtils.ajax_json(response,resultModel);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }
}
