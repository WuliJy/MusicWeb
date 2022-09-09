package com.service;

import com.dao.UserDAO;
import com.pojo.User;
import com.util.ResultModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    //功能1： 注册，向tb_user表中添加1条新记录
    public boolean register(User user) {
        boolean flag = false;
        if (user == null) {
            return flag;
        }
        try {
            int n = userDAO.add(user);
            flag = n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 功能2:  登录,
    public ResultModel login(String userPhone, String userPwd) {
        ResultModel resultModel = new ResultModel();
        try {
            User user = new User();
            user.setUserPhone(userPhone);
            user.setUserPwd(userPwd);
            System.out.println(user);
            User successUser = userDAO.findUser(user);
            System.out.println(successUser);
            if (successUser != null) {
                resultModel.setCode(1);
                resultModel.setData(successUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    //3.查询所有小分类信息（分页查询）
    public ResultModel userList(String pageNum, String pageSize) {
        ResultModel resultModel = new ResultModel();
        try {
            int page = 1;//默认第1页
            int size = 5; //默认每页显示5条
            if (Objects.nonNull(pageNum)) {
                page = Integer.parseInt(pageNum);
            }
            if (Objects.nonNull(pageSize)) {
                size = Integer.parseInt(pageSize);
            }
            List<User> userList = userDAO.fenye(page, size);
            int count = userDAO.totalNum().intValue();
            resultModel.setCode(0);//记住，必须是0
            resultModel.setMsg("");
            resultModel.setCount(count);
            resultModel.setData(userList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }
    //4.修改
    public ResultModel updateUser(User user) {
        ResultModel resultModel = new ResultModel();
        try {
            int n = userDAO.update(user);
            if (n > 0) {
                resultModel.setCode(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

    //5.删除
    public ResultModel delUser(String userID) {
        ResultModel resultModel = new ResultModel();
        try {
            int n=userDAO.delete(userID);
            if(n>0){
                resultModel.setCode(1);
                resultModel.setMsg("删除成功!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }


    public ResultModel dellAll(String ids) {
        ResultModel resultModel = new ResultModel();
        int n=0;
        try {
            if(Objects.nonNull(ids)){
                String[] idArr= ids.split(",");
                for(String id:idArr){
                    if(Objects.nonNull(id)){
                        n=userDAO.delete(id);
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

    public ResultModel findUser(String userID) {
        ResultModel resultModel = new ResultModel();
        try {
            User user = userDAO.findByPK(userID); //若方法返回的是int 设置setCode
            resultModel.setData(user);                 //若方法返回的是值，则设置setData
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }



    public ResultModel findAllUser() {
        ResultModel resultModel = new ResultModel();
        try {
            List<User> UserList = userDAO.findAll();
            resultModel.setData(UserList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultModel;
    }

}
