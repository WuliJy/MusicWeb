package com.service;

import com.dao.AdminDAO;
import com.pojo.Admin;
import com.util.ResultModel;

import java.sql.SQLException;
import java.util.Objects;

public class AdminService {
    AdminDAO adminDAO = new AdminDAO();
    //后台管理员登录
    public ResultModel adminLogin(Admin admin) {
        ResultModel resultModel = new ResultModel();//它有默认值
        try {
            if (Objects.nonNull(admin)) {
                Admin successAdmin= adminDAO.findAdmin(admin.getAdminPhone(), admin.getAdminPwd());
                if(successAdmin!=null){
                    resultModel.setCode(1);
                    resultModel.setMsg("登录成功!");
                    resultModel.setData(successAdmin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }
}
