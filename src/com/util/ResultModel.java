package com.util;

//因为layui的table模块返回的数据接口的格式固定了
public class ResultModel {
    /**
     * 状态码： 1，0等
     **/
    private int code;
    /**
     * 消息
     **/
    private String msg;
    /**
     * 复合数据，如用户对象user，集合list对象  list   对象  数组
     **/
    private Object data;
    /**
     * 数据的条数,可以用于分页展示
     **/
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "\tcode：" + code + "\t\tmsg：" + msg + "\n\tdata：" + data;
    }
}
