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
        String url="jdbc:mysql://127.0.0.1:5096/briup";
        String user="root";
        String password="root";
        Connection connection=DriverManager.getConnection(url,user,password);

        // 3、获取Statement对象
        Statement statement=connection.createStatement();

        // 4、执行SQL
        String sql="select last_name as ln,salary"+
            " from briup.s_emp limit 5";
        ResultSet resultSet=statement.executeQuery(sql);

        // 5、如果执行的是select语句，一般会有结果集
        //    处理结果集
        while(resultSet.next()){
            // 通过列名获取结果集
            String last_name=
                resultSet.getString("ln");
            int salary=
                resultSet.getInt("salary");
            System.out.println(last_name+"::"+salary);

            // 通过列的编号获取结果集
            //String lname=resultSet.getString(1);
            //int slry=resultSet.getInt(2);
            //System.out.println(lname+"::"+slry);

            // 通过列或者字段的别名来获取数据

        }

        // 6、关闭相关资源(I/O)
        resultSet.close();
        statement.close();
        connection.close();
    }
}
