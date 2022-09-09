package com.util;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

public class FileUtils {
    //从字符串中截取子字符串，指定开始索引和结束索引
    // form-data; name="img1"; filename="IMG2.jpeg"
    public static String getFileName(String disposition) {
        String filename = null;
        if (disposition != null) {
            int start = disposition.lastIndexOf("filename=\"") + 10;
            int end = disposition.length()-1;
            filename = disposition.substring(start, end);
        }
        return filename;
    }


    /**
     *
     * @param filePath 传递进来的路径
     * @return 磁盘上的图片
     */
   public static File  getFile(String filePath){
       if(filePath!=null && filePath.contains("upload/")){
           filePath=filePath.substring("upload/".length());
           filePath="D:/upload_temp/"+filePath;
       }
      return new File(filePath);
   }

    /**
     * 解决不同浏览下载图片时的中文乱码问题
     * @param filename 文件名
     * @param request  请求，请求头中可以知道用户使用的哪种浏览器
     * @return
     * @throws IOException
     */
    public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
        String agent = request.getHeader("User-Agent"); //获取浏览器
        if (agent.contains("Firefox")) {
            filename = "=?utf-8?B?"
                    + Base64.encodeBase64String(filename.getBytes("utf-8"))
                    + "?=";
        } else if (agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else if (agent.contains("Safari")) {
            filename = new String(filename.getBytes("utf-8"), "ISO8859-1");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

}
