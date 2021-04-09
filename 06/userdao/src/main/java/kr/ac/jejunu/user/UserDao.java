package kr.ac.jejunu.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final jdbcContext jdbcContext;

    public UserDao(jdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Integer id) throws SQLException {
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }

    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Integer id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    private User jdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {
        return jdbcContext.jdbcContextForGet(statementStrategy);
    }


    private void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }


    private void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
