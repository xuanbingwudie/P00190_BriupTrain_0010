package com.briup.train.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasicOperation{
    public static void main(String[] args) throws Exception{
        // 使用JDBC连接数据库的步骤：
        // 不管是什么程序，不管连接的是什么数据库，
        // 都需要以下几个步骤：

        // 1、加载驱动类/注册驱动类
        String driver="com.mysql.jdbc.Driver";
        Class.forName(driver);
        // 2、获取连接对象
        String url="jdbc:mysql://1.0.0.50:5718/briup";
        String user="root";
        String password="root";
        Connection connection=
            DriverManager.getConnection(
                url,user,password);
        // 3、获取Statement对象
        Statement statement=
            connection.createStatement();
        // 4、执行SQL
        String sql="select * from briup.s_emp";
        ResultSet resultSet=
            statement.executeQuery(sql);
        // 5、如果执行的是select语句，一般会有结果集
        //    处理结果集

        while(resultSet.next()){
            String last_name=
                resultSet.getString("last_name");
            int salary=resultSet.getInt("salary");
            System.out.println(last_name+"::"+salary);
        }
        // 6、关闭相关资源(I/O)
        connection.close();
    }
}
