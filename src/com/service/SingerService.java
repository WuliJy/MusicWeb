package com.service;

import com.dao.SingerDAO;
import com.dao.SingerImgDAO;
import com.pojo.Singer;
import com.pojo.SingerImg;
import com.util.ResultModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class SingerService {

    private SingerDAO singerDAO=new SingerDAO();
    private SingerImgDAO singerImgDAO=new SingerImgDAO();

    public ResultModel findAllSinger() {
        ResultModel resultModel=new ResultModel();
        try {
            List<Singer> singerList=singerDAO.findAll();
            resultModel.setData(singerList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    public ResultModel dellAll(String ids) {
        ResultModel resultModel=new ResultModel();
        int n=0;
        try {
            if(Objects.nonNull(ids)){
                String[] idArr= ids.split(",");
                for(String id:idArr){
                    if(Objects.nonNull(id)){
                        n=singerDAO.delete(id);
                    }
                }
            }
            if(n>0){
                resultModel.setCode(1);
                resultModel.setMsg("批量删除成功!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    public ResultModel addSinger(Singer singer,String singerImgs){
        ResultModel model=new ResultModel();
        try {
            int n=singerDAO.add(singer);
            if(n>0){
                Integer singerId=singerDAO.getLastSingerId();
                singer.setSingerId(singerId);
                if(Objects.nonNull(singerImgs)){
                    String[] paths=singerImgs.split(",");
                    for(String path:paths){
                        if(Objects.nonNull(path) && path.trim().length()>0){
                            SingerImg singerImg=new SingerImg();
                            singerImg.setImgPath(path);
                            singerImg.setSinger(singer);
                            singerImgDAO.add(singerImg);
                        }
                    }
                }
                model.setCode(1);// 添加成功
            }else{
                model.setCode(0); //失败
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    public ResultModel updateSinger(Singer singer) {
        ResultModel resultModel=new ResultModel();
        try {
            int n=singerDAO.update(singer);
            if(n>0){
                resultModel.setCode(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    public ResultModel singerListByFenYe(String pageNum,String pageSize){
        ResultModel resultModel=new ResultModel();
        int num=1;
        int size=5;
        if(Objects.nonNull(pageNum)){
            num=Integer.parseInt(pageNum);
        }
        if(Objects.nonNull(pageSize)){
            size=Integer.parseInt(pageSize);
        }
        try {
            resultModel.setCode(0);
            resultModel.setMsg("");
            resultModel.setCount(singerDAO.totalNum().intValue());
            resultModel.setData(singerDAO.fenye(num,size));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    public ResultModel deleteSinger(String singerId) {
        ResultModel resultModel=new ResultModel();
        if(Objects.nonNull(singerId)){
            try {
                singerImgDAO.deleteBySingerId(Integer.parseInt(singerId));
                int n=singerDAO.delete(Integer.parseInt(singerId));
                if(n>0){
                    resultModel.setCount(1); resultModel.setMsg("删除成功!");
                }else{
                    resultModel.setCode(0); resultModel.setMsg("删除失败!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultModel;
    }

    //根据英雄ID查询英雄信息，并且需要把图片查询出来
    public ResultModel selectSingerAndImg(String singerId) {
        ResultModel model=new ResultModel();
        try {
            int id=0;
            if(Objects.nonNull(singerId)){
                id=Integer.parseInt(singerId);
                Singer singer=singerDAO.findByPK(id);
                List<SingerImg> singerImgList=singerImgDAO.findImgBySingerId(id);
                singer.setSingerImgList(singerImgList);
                model.setCode(1);
                model.setMsg("");
                model.setData(singer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

}
