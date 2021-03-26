package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    private final ConnectionMaker connectionMaker;
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User findById(Integer id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();

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


    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
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
//    public Connection getConnection() throws ClassNotFoundException, SQLException {
//        // Where is the data stored? MySQL
//
//        // Load Driver
//
//        // Make Connection
//        return connectionMaker.getConnection();
//    }
}
