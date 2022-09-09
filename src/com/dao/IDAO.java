package com.dao;

import com.util.DruidPool;
import org.apache.commons.dbutils.QueryRunner;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<E> {

    public static final QueryRunner qr=new QueryRunner(DruidPool.getDataSource());

    public int add(E obj) throws SQLException;

    public int update(E obj) throws SQLException;

    public int delete(Serializable pk) throws SQLException;

    public E findByPK(Serializable pk) throws SQLException;

    public List<E> findAll() throws SQLException;

    // pageNum 当前页码   pageSize 每页显示条数
    public List<E> fenye(int pageNum, int pageSize) throws  SQLException;

    //求总记录数
    public Long totalNum() throws  SQLException;

}
