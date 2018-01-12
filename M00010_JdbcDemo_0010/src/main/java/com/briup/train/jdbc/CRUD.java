package com.briup.train.jdbc;

import com.briup.train.util.DBUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    @Test
    public void delete() throws Exception{
        Connection conn=DBUtils.getConn();
        Statement stat=conn.createStatement();
        // 删除last_name是李四的那一行数据
        String sql="delete from briup.s_emp " +
            "where briup.s_emp.last_name='李四'";
        stat.execute(sql);
    }

    @Test
    public void select() throws Exception{
        Connection conn=
            DBUtils.getConn();
        Statement stat=
            conn.createStatement();
        // 查询last_name为Havel的信息
        String sql="select last_name,salary " +
            "from briup.s_emp " +
            "where last_name='Havel'";
        ResultSet rs=
            stat.executeQuery(sql);
        while(rs.next()){
            String last_name=
                rs.getString("last_name");
            String salary=
                rs.getString("salary");
            System.out.println(last_name+"::"+salary);
        }
    }

    // SQL注入攻击的问题
    // 查询last_name为Havel的信息
    public static void sqlinject(String name) throws Exception{
        Connection conn=
            DBUtils.getConn();
        Statement stat=
            conn.createStatement();
        String sql="select last_name,salary " +
            "from briup.s_emp " +
            "where last_name='"+name+"'";
        // ' or 1 or '
        ResultSet rs=stat.executeQuery(sql);
        while(rs.next()){
            String last_name=
                rs.getString("last_name");
            String salary=
                rs.getString("salary");
            System.out.println(
                last_name+"=="+salary);
        }
    }

    public static void main(String[] args) throws Exception{
        sqlinject("' or 1 or '");
        System.out.println("-------------------");
        sqlinject("5");
    }

    //PreparedStatement

    @Test
    public void select2(String name) throws Exception{
        Connection conn=DBUtils.getConn();
        // 获取Statement对象是通过createStatement()
        // 获取PreparedStatement通过preparedStatement()
        String sql="select last_name,salary " +
            "from briup.s_emp " +
            "where last_name=?";
        // 在预编译SQL中，使用“?”做占位符
        // 该占位符所代表的具体数据值，需要使用
        // PreparedStatement对象通过set方法设置
        PreparedStatement pstat=
            conn.prepareStatement(sql);
        pstat.setString(1,name);
        ResultSet rs=pstat.executeQuery();
        while(rs.next()){
            String last_name=rs.getString(1);
            int salary=rs.getInt(2);
            System.out.println(last_name+"::"+salary);
        }
    }
}
