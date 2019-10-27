package com.zjm.database.mysql.jdbc;

import java.sql.*;

/**
 * @author:小M
 * @date:2019/5/20 11:14 PM
 */
public class JDBCTest {

    public static void main(String[] args) throws Exception {

        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver" , false , JDBCTest.class.getClassLoader());

        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/interview?useUnicode=true"
            + "&characterEncoding=UTF-8&useSSL=false" +
                "&autoReconnect=true" +
                "&failOverReadOnly=false&allowPublicKeyRetrieval=true" +
                "&useServerPrepStmts=true" + // 开启预编译
                "&cachePrepStmts=true", // 多个相同sql预编译一次，注意：每次使用PreparedStatement对象后都要关闭该PreparedStatement对象流，否则预编译后的函数key是不会缓存的。
                "test" ,
                "test");
        connection.setAutoCommit(false);

        // 3. 创建Statement
        PreparedStatement statement = connection.prepareStatement("select * from catalog where id = ?");
        statement.setInt( 1 , 50);

        // 4. 执行SQL
        ResultSet resultSet = statement.executeQuery();

        // 5. 处理结果集
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String code = resultSet.getString("code");
            System.out.println("id="+id + " , code=" + code);
        }

        // 6.提交或回滚
        connection.commit();
        connection.rollback();

        // 7.关闭资源
        resultSet.close();
        statement.close();
        connection.close();

    }

    public static class Test1{
        public static void main(String[] args) {

            String s = "abc";
            System.out.println(s.substring(0 , 3));
        }


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
