package com.dao;

import com.pojo.Admin;
import com.util.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminDAO {
    private QueryRunner qr = new QueryRunner(DruidPool.getDataSource());
    public Admin findAdmin(String adminPhone, String adminPwd)
            throws SQLException {
        String sql = "select * from tb_admin where adminPhone=? and adminPwd=?";
        return qr.query(sql, new BeanHandler<>(Admin.class),
                adminPhone, adminPwd);
    }
}
