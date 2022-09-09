package com.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//阿里连接池
public class DruidPool {
    //声明一个数据库连接池对象
    private static DruidDataSource dataSource;

    //事务专用连接 （缓存）
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    static {
        try {
            //创建一个空属性列表
            Properties properties=new Properties();
            //使用字节输入流指向我们配置文件druid.properties
            InputStream is=DruidPool.class.getClassLoader().
                    getResourceAsStream("druid.properties");
            //把属性文件中配置信息，存入到空的属性列表中
            properties.load(is);
            dataSource=(DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //使用连接池返回一个连接对象
    public static Connection getConnection(){
        Connection con=null;
        try {
            con = tl.get(); //从缓存中获取
            //当con不等于null，说明已经调用过beginTransaction（），表示开启了事务
            if(con != null)return con;
            //若con为null，说明缓存中没有，就从数据库连接池中获取
            con=dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    //返回连接池对象 public QueryRunner(DataSource ds)
    public static DruidDataSource getDataSource() {
        return dataSource;
    }
    /*
     * 释放连接
     * */
    public static void releaseConnection(Connection connection)throws SQLException{
        Connection con = tl.get();
        /*
         * 判断它是否是事务专用，如果是，就不关闭
         * 如果不是事务专用，那就要关闭
         * */
        //如果con == null，说明现在没有事务，那么connection一定不是事务专用的
        if(con == null) connection.close();
        //如果con != null，说明有事务，那么需要判断连接是否与con相等，若不等，说明参数连接不是事务专用连接
        if (con != connection) connection.close();
    }
    /*
     * 开启事务
     * 1、获取一个Connection,设置它的setAutoCommit(false)
     * 2、还要保证dao中使用的连接是我们刚刚创建的
     * ------------
     * 1、创建一个Connection，设置为手动提交
     * 2、把这个Connection给dao用
     * 3、还要让commitTransaction或rollbackTransaction可以获取到
     * */
    public static void beginTransaction() throws SQLException {
        Connection con = tl.get();
        if (con != null) throw new SQLException("已经开启了事务，就不要重新开启了");
        /*
         * 1、给con赋值
         * 2、给con设置为手动提交
         * */
        con = getConnection();//给con赋值，表示事务已经开始了
        con.setAutoCommit(false);

        tl.set(con);//把当前线程的连接保存起来
    }
    /*
     * 提交事务
     * 1、获取beginTransaction提供的Connection,然后调用Commit()方法
     * */
    public static void commitTransaction() throws SQLException {
        Connection con = tl.get();//获取当前线程的专用连接
        if (con == null) throw new SQLException("还没有开启事务，不能提交！");
        /*
         * 1、直接使用con.commit()
         * */
        con.commit();
        con.close();
        //把它设置为null，表示事务已经结束了，下次再去调用getConnection（）返回的就不是con了
        tl.remove();//从tl中移除连接。
    }
    /*
     * 提交事务
     * 1、获取beginTransaction提供的Connection,然后调用rollbacK()方法
     * */
    public static void rollbackTransaction() throws SQLException {
        Connection con = tl.get();
        if (con == null) throw new SQLException("还没有开启事务，不能滚回！");
        /*
         * 1、直接使用con.rollback()
         * */
        con.rollback();
        con.close();
        tl.remove();
    }

}
