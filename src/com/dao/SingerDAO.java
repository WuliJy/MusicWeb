package com.dao;

import com.pojo.Singer;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class SingerDAO implements IDAO<Singer> {
    @Override
    public int add(Singer obj) throws SQLException {
        String sql="insert into tb_singer(singerName,singerSex,singerLabel,singerInitial,star,singerIntro) values(?,?,?,?,?,?)";
        Object[] params={obj.getSingerName(),obj.getSingerSex(),obj.getSingerLabel(),obj.getSingerInitial(),obj.getStar(),obj.getSingerIntro()};
        return qr.update(sql,params);
    }

    //获取你刚插入成功的ID
    public Integer  getLastSingerId() throws  SQLException{
        return qr.query("select max(singerId) from tb_singer",new ScalarHandler<>());
    }

    @Override
    public int update(Singer obj) throws SQLException {
        return qr.update("update tb_singer set star=?,singerIntro=? where singerId=?",
                obj.getStar(),obj.getSingerIntro(),obj.getSingerId());
    }

    @Override
    public int delete(Serializable pk) throws SQLException {
        return qr.update("delete from tb_singer where singerId=?",pk);
    }

    @Override
    public Singer findByPK(Serializable pk) throws SQLException {
        return qr.query("select * from tb_singer where singerId=?",
                new BeanHandler<>(Singer.class),pk);
    }

    @Override
    public List<Singer> findAll() throws SQLException {
        return qr.query("select * from tb_singer",new BeanListHandler<>(Singer.class));
    }


    @Override
    public List<Singer> fenye(int pageNum, int pageSize) throws SQLException {
        String sql="select * from tb_singer order by singerId limit ?,?";
        Object[] params={(pageNum-1)*pageSize,pageSize};
        return qr.query(sql,new BeanListHandler<>(Singer.class),params);
    }

    @Override
    public Long totalNum() throws SQLException {
        return qr.query("select count(*) from tb_singer",
                new ScalarHandler<>());
    }
}
