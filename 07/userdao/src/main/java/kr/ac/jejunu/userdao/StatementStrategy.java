package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    abstract PreparedStatement makeStatement(Connection connection) throws SQLException;
//    {
//        PreparedStatement preparedStatement;
//        preparedStatement = connection.prepareStatement("update userinfo set name=?, password=? where id=?");
//        preparedStatement.setString(1, user.getName());
//        preparedStatement.setString(2, user.getPassword());
//        preparedStatement.setInt(3, user.getId());
//        return preparedStatement;
//    }
}