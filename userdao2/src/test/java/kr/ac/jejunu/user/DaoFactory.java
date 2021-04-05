package kr.ac.jejunu.user;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;


// DI - Spring이 new를 하여 UserDao에 전달

@Configuration // DaoFactory는 설정을 위한
public class DaoFactory {
    // Property 주입
    // 배포 환경에 따라 달라지는 변수
    @Value("${db.classname}")
    private String className;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean // Bean의 역할 -> UserDao를 new 하는 역할
    public UserDao userDao() {
        return new UserDao(dataSource());
    }

//    public UserDao getUserDao() {
//        return new UserDao(getConnectionMaker());
//    }

//    @Bean
//    public ConnectionMaker connectionMaker() {
//        return new JejuConnectionMaker();
//    }

    // DataSource는 Connection과 관련된 여러가지 인터페이스를 제공한다.
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        // throws 안하고 try-catch로 묶는 이유
        // throws를 하면 이를 호출하는 Data Client도 이를 받아야 하기 때
        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
