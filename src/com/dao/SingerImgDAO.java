package com.dao;


import com.pojo.SingerImg;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class SingerImgDAO implements IDAO<SingerImg> {

    @Override
    public int add(SingerImg obj) throws SQLException {
        return qr.update("insert into tb_singer_img(imgPath,singerId) values(?,?)",
                obj.getImgPath(),obj.getSinger().getSingerId());
    }

    @Override
    public int update(SingerImg obj) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Serializable pk) throws SQLException {
        return 0;
    }

    @Override
    public SingerImg findByPK(Serializable pk) throws SQLException {
        return null;
    }

    @Override
    public List<SingerImg> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<SingerImg> fenye(int pageNum, int pageSize) throws SQLException {
        return null;
    }

    @Override
    public Long totalNum() throws SQLException {
        return null;
    }

    public int deleteBySingerId(int singerId) throws SQLException {
        return qr.update("delete from tb_singer_img where singerId=?",singerId);
    }

    public List<SingerImg> findImgBySingerId(int id)throws SQLException {
        return qr.query("select * from tb_singer_img where singerId=?",
                new BeanListHandler<>(SingerImg.class),id);
    }

}
