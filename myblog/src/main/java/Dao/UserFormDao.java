package Dao;

import models.UserInfo;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFormDao {

    public int add(String username,String password) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();
        String sql = "insert into userform(username,password) values(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,username);
        statement.setString(2,password);

        result = statement.executeUpdate();

        return result;
    }

    public UserInfo getUser(String username, String password) throws SQLException {
        UserInfo userInfo = new UserInfo();
        Connection connection = DBUtils.getConnection();
        String sql = "select * from userform where username =? and password =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,username);
        statement.setString(2,password);


        //查询数据库
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            userInfo.setId(resultSet.getInt("id"));
            userInfo.setUsername(resultSet.getString("username"));
            userInfo.setPassword(resultSet.getString("password"));
        }
        DBUtils.close(connection,statement,resultSet);
        return userInfo;
    }
}
