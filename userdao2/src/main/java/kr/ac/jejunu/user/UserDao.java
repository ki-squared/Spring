package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        // Where is the data stored? MySQL

        // Load Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Make Connection
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/kakao?" +
                        "characterEncoding=utf-8&serverTimezone=UTC"
                ,"root","root"
        );

        // Make Query - Use PreparedStatement to cache only one sentence(the line right below)
        PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userinfo where id=?");
        preparedStatement.setInt(1, id); //  id will be placed in ? position

        // Execute
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next(); // move to next to point the log

        // Map Result
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setName(resultSet.getString("password"));

        // Free Resources
        resultSet.close();
        preparedStatement.close();
        connection.close();

        // Return result
        return user;
    }
}
