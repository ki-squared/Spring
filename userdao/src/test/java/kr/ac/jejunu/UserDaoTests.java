package kr.ac.jejunu;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void get() {
        // 우리는 데이터를 조회할거야! 그걸 위한 클래스가 필요해
        Integer id = 1;
        String name = "Jade";
        String password = "1234";

        UserDao userDao = new UserDao();
        // 사용자 정보가 필요해! 아이디 값을 주면 조회하는 클래스에서 user object가 나오게끔 할거야.
        User user = userDao.findById(id);

        // 테스트 - 요구사항에 대한 모든 Test Case 작성
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
}
