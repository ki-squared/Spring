package kr.ac.jejunu.user;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;

public class UserDaoTests {
    @Test
    public void get() throws ClassNotFoundException, SQLException {
        Integer id = 1;
        String name = "Jade";
        String password = "1234";
        DaoFactory daoFactory = new DaoFactory();

        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    // 새 요구사항! DB에 사용자를 추가하는 기능을 넣어달라!
    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "Jade";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        userDao.insert(user);

        User inserted_user = userDao.findById(user.getId());
        assertThat(user.getId(), greaterThan(0));
        assertThat(inserted_user.getId(), is(user.getId()));
        assertThat(inserted_user.getName(), is(user.getName()));
        assertThat(inserted_user.getPassword(), is(user.getPassword()));

    }

    @Test
    public void getHalla() throws ClassNotFoundException, SQLException {
        Integer id = 1;
        String name = "Jade";
        String password = "1234";
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao =daoFactory.getUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    // 새 요구사항! DB에 사용자를 추가하는 기능을 넣어달라!
    @Test
    public void insertHalla() throws SQLException, ClassNotFoundException {
        String name = "Jade";
        String password = "1234";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
        userDao.insert(user);

        User inserted_user = userDao.findById(user.getId());
        assertThat(user.getId(), greaterThan(0));
        assertThat(inserted_user.getId(), is(user.getId()));
        assertThat(inserted_user.getName(), is(user.getName()));
        assertThat(inserted_user.getPassword(), is(user.getPassword()));

    }

}
