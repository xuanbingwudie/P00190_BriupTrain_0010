package com.briup.train.jdbc;

import com.briup.train.util.DBUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.junit.Test;

public class CRUD{

    // 做增加数据操作
    @Test   // Annotation   注解
    public void add() throws Exception{
        // 注册驱动
        Class.forName(
            "com.mysql.jdbc.Driver");
        // 获取连接对象
        Connection conn=
        DriverManager.getConnection(
          "jdbc:mysql://127.0.0.1:5096",
          "root","root");
        // 获取Statement对象
        Statement stat=
            conn.createStatement();
        // 执行SQL语句
        String sql="insert into " +
            "briup.s_emp(id,last_name,salary) " +
            "values(600,'张三',2000)";
        //stat.executeQuery(sql)  主要查询使用
        //stat.executeUpdate(sql) 主要做更新使用
        stat.execute(sql);     // 主要做添加和删除数据使用

        // 处理结果集，执行的是insert语句，
        // 没有结果集，不用处理

        // 关闭相关资源
        stat.close();
        conn.close();
    }

    // 做更新操作
    @Test
    public void update() throws Exception{
        // 使用封装的DBUtils的getConn方法获取连接对象
        Connection conn=DBUtils.getConn();
        // 获取Statement对象
        Statement stat=conn.createStatement();
        // 执行SQL
        String sql="update briup.s_emp " +
            "set briup.s_emp.last_name = '李四' " +
            "where briup.s_emp.id=600";
        stat.executeUpdate(sql);
    }

    // SQL注入攻击
}
