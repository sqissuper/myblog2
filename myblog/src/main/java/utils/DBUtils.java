package utils;

import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    private static MysqlDataSource mysqlDataSource = null;


    public static Connection getConnection() throws SQLException {
        if(mysqlDataSource == null) {
            mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/myblog?charactionEncoding=utf-8&useSSL=true");
            mysqlDataSource.setUser("root");
            mysqlDataSource.setPassword("123456");
        }
        return mysqlDataSource.getConnection();
    }


    public static void close (Connection connection, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        if(resultSet != null) resultSet.close();
        if(statement != null) statement.close();
        if(connection != null) connection.close();
    }
}
