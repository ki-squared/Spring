package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    public User findById(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();

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
        user.setPassword(resultSet.getString("password"));

        // Free Resources
        resultSet.close();
        preparedStatement.close();
        connection.close();

        // Return result
        return user;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        // Where is the data stored? MySQL

        // Load Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Make Connection
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/kakao?" +
                        "characterEncoding=utf-8&serverTimezone=UTC"
                , "root", "root"
        );
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();

        user.setId(resultSet.getInt(1));
        preparedStatement.close();
        connection.close();
    }
}
