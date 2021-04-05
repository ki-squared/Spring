package kr.ac.jejunu.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;

public class UserDaoTests {
    String name = "jeju";
    String password = "jejupw";

    private static UserDao userDao;

    // Spring을 사용하기!
    @BeforeAll
    public static void setup() {
        // ApplicationContext : Bean을 관리
        // Bean, Configuration 모두 Annotation > Annotation을 이용해서 Bean을 관리 -> Strategy Pattern
        // DaoFactory에 Bean의 정의가 Annotation으로 되어 있음!
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class); // userDao는 메소드 이름! > Dependency Lookup

    }

    @Test
    public void get() throws ClassNotFoundException, SQLException {
        Integer id = 1;
        // Spring이 하게 된다!
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        // Spring이 하게 된다.
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.getUserDao();
        userDao.insert(user);
        assertThat(user.getId(), greaterThan(0));

        User inserted_user = userDao.get(user.getId());
        assertThat(inserted_user.getName(), is(user.getName()));
        assertThat(inserted_user.getPassword(), is(user.getPassword()));

    }

    @Test
    public void update() throws SQLException {
        // Test Data 등록
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        String updatedName = "민주";
        String updatedPassword = "1111";
        user.setName(updatedName);
        user.setPassword(updatedPassword);

        userDao.update(user);
        User updatedUser = userDao.get(user.getId());
        assertThat(updatedUser.getName(), is(updatedName));
        assertThat(updatedUser.getPassword(), is(updatedPassword));
    }

    @Test
    public void delte() throws SQLException {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        userDao.delete(user.getId());

        User deletedUser = userDao.get(user.getId());
        assertThat(deletedUser, IsNull.nullValue());
    }

}
