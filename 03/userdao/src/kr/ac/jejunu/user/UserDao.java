package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        // DB - MySQL
        // Driver Loading
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/kakao?characterEncoding=utf-8&serverTimezone=UTC", "root", "root");

        // Make Query
        PreparedStatement preparedStatement = connection.prepareStatement("select id, name, password from userinfo where id=?");
        preparedStatement.setInt(1, id);

        // Execute Query
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next(); // move result-set cursor

        // Map Result to User
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        // Free Resources
        preparedStatement.close();
        resultSet.close();
        connection.close();

        // Return Result
        return user;
    }

}
