package com.dao;

import com.pojo.User;
import com.util.DruidPool;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements IDAO<User> {
    private QueryRunner qr=new QueryRunner(DruidPool.getDataSource());

    //注册
    public int add(User user) throws SQLException  {
        String sql="insert into tb_user(userPhone,userName,userPwd) values(?,?,?)";
        return qr.update(sql,user.getUserPhone(),user.getUserName(),user.getUserPwd());
    }

    //登录
    public User findUser(User user) throws SQLException {
        return qr.query("select * from tb_user where userPhone=? and userPwd=?",
                new BeanHandler<>(User.class),user.getUserPhone(),user.getUserPwd());
    }

    @Override
    public int update(User user) throws SQLException {
        return qr.update("update tb_user set  userName=? where userID=?",
               user.getUserName(),user.getUserID());
    }

    @Override
    public int delete(Serializable pk) throws SQLException {
        return qr.update("delete from tb_user where userID=?",
                pk);
    }

    @Override
    public User findByPK(Serializable pk) throws SQLException {
        String sql="select * from tb_user where userID=?";
        return qr.query(sql,new ResultSetHandler<User>(){
            @Override
            public User handle(ResultSet rs) throws SQLException {
                User user=null;
                while(rs.next()){
                    BeanProcessor bp=new BeanProcessor();
                    user=bp.toBean(rs,User.class);
//                    user.setUser(bp.toBean(rs,User.class));
                }
                return user;
            }
        },pk);
    }

    @Override
    public List<User> findAll() throws SQLException {
        return qr.query("select * from tb_user",
                new BeanListHandler<>(User.class));
    }

    @Override
    public List<User> fenye(int pageNum, int pageSize) throws SQLException {
        return qr.query("select * from tb_user where userID order by userID limit ?,?",
                new BeanListHandler<>(User.class),pageNum,pageSize);

    }

    @Override
    public Long totalNum() throws SQLException {
        return qr.query("select count(*) from tb_user",
                new ScalarHandler<>()); // ScalarHandler用于处理单个值的处理器
    }
}
