package com.zjm.database.mysql.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class DruidTest {

    public static void main(String[] args) throws Exception{

        Map<String,String> config = new HashMap<>();
        config.put("driverClassName" , "com.mysql.cj.jdbc.Driver");
        config.put("url" , "jdbc:mysql://localhost:3306/interview?useUnicode=true"
                        + "&characterEncoding=UTF-8&useSSL=false" +
                        "&autoReconnect=true" +
                        "&failOverReadOnly=false&allowPublicKeyRetrieval=true" +
                        "&useServerPrepStmts=true" + // 开启预编译
                        "&cachePrepStmts=true");
        config.put("initialSize" , "5");
        config.put("maxActive" , "10");
        config.put("maxWait" , "3000");
        config.put("maxIdle" , "6");
        config.put("minIdle" , "3");
        config.put("username" , "root");
        config.put("password" , "aaa38324836");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(config);

        Connection connection = dataSource.getConnection();

        connection.setAutoCommit(false);

        // 3. 创建Statement
        PreparedStatement statement = connection.prepareStatement("select * from catalog");

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
}
