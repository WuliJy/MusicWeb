package com.service;

import com.dao.SingerDAO;
import com.dao.SingerImgDAO;
import com.pojo.Singer;
import com.pojo.SingerImg;
import com.util.ResultModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class IndexService {
    private SingerDAO singerDAO=new SingerDAO();
    private SingerImgDAO singerImgDAO=new SingerImgDAO();
    public ResultModel singerList() {
        ResultModel resultModel=new ResultModel();
        try {
            List<Singer> singerList=singerDAO.findAll();
            if(Objects.nonNull(singerList) && singerList.size()>0){
                 for(Singer singer:singerList){
                    List<SingerImg>  singerImgList=singerImgDAO.findImgBySingerId(singer.getSingerId());
                     singer.setSingerImgList(singerImgList);
                 }
            }
            resultModel.setData(singerList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    public ResultModel singerListByFenYe(String pageNum,String pageSize) {
        ResultModel resultModel=new ResultModel();
        try {
            int page=1; int size=2;
            if(Objects.nonNull(pageNum)){
                page=Integer.parseInt(pageNum);
                size=Integer.parseInt(pageSize);
            }
            List< Singer>  singerList= singerDAO.fenye(page,size);
            if(Objects.nonNull( singerList) &&  singerList.size()>0){
                for( Singer  singer: singerList){
                    List< SingerImg>  singerImgList= singerImgDAO.findImgBySingerId( singer.getSingerId());
                    singer.setSingerImgList( singerImgList);
                }
            }
            resultModel.setCode(0);
            resultModel.setMsg("");
            resultModel.setData( singerList);
            resultModel.setCount( singerDAO.totalNum().intValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    public ResultModel findSingerDetail(String singerId) {
        ResultModel resultModel=new ResultModel();
        try {
            Singer  singer=singerDAO.findByPK(singerId);
            List< SingerImg> singerImgList=singerImgDAO.findImgBySingerId(Integer.parseInt(singerId));
            singer.setSingerImgList(singerImgList);
            resultModel.setData(singer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }
}
