package com.briup.train.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

// 为了让该类具有通用性
// 将一些参数以配置文件的形式提供
// 能随时修改的数据有：
//      数据库驱动的类全名
//      连接数据库的url
//      用户名和密码
public class DBUtils{

    // 封装JDBC获取连接的部分
    public static Connection getConn() throws Exception{
        // 需要创建Properties对象
        Properties prop=new Properties();
        // 加载配置文件
        prop.load(ClassLoader.getSystemResourceAsStream("db.properties"));
        // 获取配置文件中的相应的属性值
        String driver=prop.getProperty("driver");
        String url=prop.getProperty("url");
        String user=prop.getProperty("user");
        String password=prop.getProperty("password");

        // 加载JDBC驱动/注册JDBC驱动
        Class.forName(driver);
        // 获取连接对象
        return DriverManager.getConnection(url,user,password);
    }
}
