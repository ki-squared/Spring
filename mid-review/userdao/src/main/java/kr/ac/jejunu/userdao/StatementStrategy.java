package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    abstract PreparedStatement makeStatement(Connection connection) throws SQLException;
}
