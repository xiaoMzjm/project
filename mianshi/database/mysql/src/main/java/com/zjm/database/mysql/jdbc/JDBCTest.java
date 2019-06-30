package com.zjm.database.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.tools.internal.xjc.model.CClass;

/**
 * @author:小M
 * @date:2019/5/20 11:14 PM
 */
public class JDBCTest {

    public static void main(String[] args) throws ClassNotFoundException,SQLException {

        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver" , false , JDBCTest.class.getClassLoader());

        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/interview?useUnicode=true"
            + "&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false" , "test" , "test");

        // 3. 创建Statement
        Statement statement = connection.createStatement();

        // 4. 执行SQL
        ResultSet resultSet = statement.executeQuery("select * from catalog");

        // 5. 处理结果集
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String code = resultSet.getString("code");
            System.out.println("id="+id + " , code=" + code);
        }

        // 6. 关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }

    static class InsertClass{

        public static void main(String[] args) throws Exception{
            // 1. 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver" , false , JDBCTest.class.getClassLoader());

            // 2. 获取连接
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true"
                    + "&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false" , "test" , "test");

            // 3. 创建Statement
            Statement statement = connection.createStatement();

            // 4. 执行SQL
            for(int i = 0 ; i < 2000000 ; i ++) {
                String sql = "insert into bigtable(name,age) values('name" + i + "'," + i + ")";
                statement.execute(sql);
            }


            // 6. 关闭连接
            statement.close();
            connection.close();
        }

    }
}
