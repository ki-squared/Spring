# Kakao Track - Spring Framework

> 카카오 회사에서 웹 기반 서비스로 가장 많이 활용하고 있는 기술들을 배우며,
> TDD를 코딩 습관으로 녹이는 방법을 배우는 강좌.

- 개발 언어 : Java
- 프레임워크 : Spring (개발 노하우 → 디자인 패턴 → 프레임워크)
- SW 개발 프로세스 : TDD(Test-Driven-Development)

---

## 1. 개발환경 세팅

- Week - 1
    - 개발 현장에 관한 소개
    - Github 레퍼지토리 생성 : [https://github.com/ki-squared/spring](https://github.com/ki-squared/spring)
    - IntelliJ Ultimate 버전 업그레이드
    - MySQL 설치

## 2. TDD(Test-Driven-Development)

- Week - 2
    - Known To Unknown

        : 아는 것 → 모르는 것 

        : 추상적 요구 → 구체적 요구

        🌱 **참고 - 1**

        ```
        Object Oriented 

        - Encapsulation : 객체의 Field와 Method를 하나로 묶고, 실제 구현 내용 일부를 은닉
        - Abstraction : 객체의 설계도(이또한 class)를 만드는 것
        - Inheritance : 상위 클래스의 모든 멤버를 하위 클래스가 물려 받는 것, 재사용성 및 간결성 향상
        - Polymorphism : 여러 형태를 가질 수 있는 능력, 한 타입의 참조변수로 여러 타입의 객체를 참조
        ```

         

        **🌱 참고 - 2**

        ```
        Object Oriented Design Principle - SOLID

        - SRP(Single Responsibility Principle) - Class, Method는 한가지 책임만을 가진다.
        - OCP(Open/Close Principle) - 변경에는 닫혀있고, 확장에는 열려있어야 한다. 
        - LSP(Liskov Substitution Principle) - 자식 Object는 부모 Object로의 치환 가능하다.
        - ISP(Interface Segregation Principle) - Interface는 다양한 기능으로 분리해야 한다.
        - DIP(Dependency Inversion Principle) - Spring에서 다룰 내용
        ```

        ```java
        /* LSP */
        String extends Object

        String a = "Hello"
        Object b = (Object) a;n   // 부모 Object로 치환이 가능하다.
        ```

        ```java
        /* ISP */

        interface a {
        	a();
        	b();
        }

        interface b {
        	c();
        }

        class ac implements a, b {
        	a(){...}
        	b(){...}
        	c(){...}
        }
        ```

    - Seperate Of Concern

## 3. DI(Dependency Injection, IoC)

- Week - 3

    **🌱 참고**

    ```
    Design Pattern

    - Template Method Pattern
    - Factory Method Pattern
    - Strategy Pattern
    - Template/Callback Pattern
    ```

    **요구사항**

    → 전달: ID / 반환: 저장된 사용자 정보의 이름, 패스워드

    → 실습 코드 : /03

    - **Gradle**

        : "Build" → clean, javac compile, test, packaging, deploy

        : ANT → Maven → Gradle

        : Groovy Based Language

        : Easy Extension

        : Wrapper - Remove Gradle Dependency

        → 실습 코드 : /03

    - **DI(Dependency-Injection)**

        : 하나의 객체가 다른 객체의 의존성을 제공하는 테크닉

        : 객체의 생성과 사용의 관심을 분리하여 가독성, 코드 재사용을 높인다.
        
 ## 4. DI & Spring

- Week - 4

    요구사항 1 : 사용자 추가 기능 → UserDaoTests에 insert() 추가

    - Extract Method

         DB Connection 연결하는 부분과 같이 중복 사용하는 기능 부분 추출

        ```
        option+command+M -> Extract Method
        ```


    요구사항 2 : DB 분리! UserDao가 Connection을 맺는 DB가 분리되어야 한다. 

    - Abstraction

        - Template Method Pattern :  abstract class에 대한 상속 기반의 메소드 구현 패턴

        - Factory Method Pattern : 상속 기반의 추상화를 통해 특정 Object에 대해 자식이 결정

        ```
        ex. Connection에 대해 JejuUserDao와 HallaUserDao, 즉 UserDao의 자식이 결정
        ```


    요구사항 3 : 상품 등록 기능이 추가!

    - Refactoring

        → 현재 중복되는 기능들을 Refactoring!

    - Interface

        추상화된 Method 밖에 존재하지 않는 Interface "ConnectionMaker"

        → Class-Level 추상화

    - Dependency

        ```java
        private final ConnectionMaker = new JejuConnectionMaker();
        // Dependency 발생!
        // UserDao는 ConnectionMaker를 사용하고 싶은 것, JejuConnection Maker X
        ```

        → "new"할 생성자를 만든다. 

        → JejuConnectionMaker는 UserDao가 아닌 UserDaoTests(사용자)가 만들어 사용

    - Strategy Pattern


        Context : 변하지 않는 것

        Strategy : 변하는 것

        ```
        UserDao에서 변하지 않는 부분 -> PrepareStatement ... return user;
        UserDao에서 변하는 부분 -> Connection
        ```

    - Client Dependency 제거

        : DaoFactory에 Dependency 주입 → **DI(Dependency Injection)**

        : DaoFactory가 바로 **Spring**!

        → Spring의 Core는 의존성에 대한 처리!       
        
 ## 5. Spring

- Week - 5
    - Spring 으로 바꾸는 작업

        ```groovy
        /* build.gradle */

        ext {
        	springVersion = '5.2.5.RELEASE'
        }

        dependencies {
        	implementation "org.springframework:spring-core:${springVersion}"
        	implementation "org.springframework:spring-context:${springVersion}"
        }
        ```

        : DaoFactory를 Spring으로 변환

        ```java
        @Configuration
        public class DaoFacotory {
        	// DaoFactory > 설정

        	@Bean
        	public UserDao getUserDao() {
        		return new UserDao(connectionMaker());
        	}

        	@Bean
        	public ConnectionMaker connectionMaker() {
        		return new JejuConnectionMaker();
        	}
        }
        ```

        - Annotations

            @Configuration

            : Bean을 정의하는 것

            @Bean

            : Dependency를 가진 "new"를 Spring이 해주는 Object Instance 생성

        : UserDaoTests

        ```java
        @BeforeAll
            public static void setup() {
                ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        				userDao = applicationContext.getBean("userDao", UserDao.class);    
        }
        ```

        : ApplicationContext는 Bean을 관리

        : DaoFactory라는 클래스에 Bean이 Annotation으로 정의되어 있음을 표시

    - Dependency Lookup

        Instance를 new하며 dependency를 관리하는 것에 DI가 완료된 Instance를 Lookup

    - Spring

        - ConnectionMaker → jdbc library ⇒ DataSource

           : DataSource는 DB Connection과 관련된 여러가지 인터페이스를 제공 

        - Connection과 관련한 field는 배포한 환경에 따라 환경변수로 설정하도록 변환

           : Property Injection

           : 프로그램이 DB Connection과 관련한 Dependency를 가질 필요가 없게 된다. 

        ```java
        @Value("db.classname")
        private String classname;
        ```

        ```
        Edit Configurations

        DB_CLASSNAME > com.mysql.cj.jdbc.Driver 
        DB_URL > jdbc:mysql://localhost/kakao?characterEncoding=utf-8&serverTimezone=UTC
        DB_USERNAME > root
        DB_PASSWORD > root
        ```

    - 추가 요구사항

        : 사용자 수정, 사용자 삭제 기능 추가

        1) Test Case 생성

        2) UserDao Method 추가

        3) Test & Debug

## 6. Refactoring

- Week - 6
    - Refactoring 시각

        StatementStrategy를 사용하여 PreparedStatement를 생성하는 부분 변형

        ```java
        public interface StatementStrategy {

            public PreparedStatement makeStatement(Object object, Connection connection) throws SQLException;
        }
        ```

        : abstract method로 기능을 빼면 makeStatement는 기능 한가지 만을 구현할 수 있다. 

        : Connection을 맺는 부분은 같은 기능(DB 소스만 다름)이었으나 PreparedStatement는 다른 기능을 수행하기 때문에 interface라는 더 상위 추상화를 진행한다. 

        : JdbcContext

    - Template/Callback Pattern

        ```java
        public void update(User user) throws SQLException {
                jdbcContext.jdbcContextForUpdate(connection -> {
                    PreparedStatement preparedStatement;
                    preparedStatement = connection.prepareStatement("update userinfo set name=?, password=? where id=?");
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setInt(3, user.getId());
                    return preparedStatement;
                });
            }
        ```

        : Java는 Method의 Parameter로 Method를 받을 수 없다. 

        : JavaScript는 Functional Programming Language이나 Java는 아니다.

        : 다음과 같은 노테이션은 람다로, Java8부터 Java를 Functional Programming Language처럼 사용할 수 있도록 한다. 

        : 지금 실행되는 것이 아니고, Method를 Parameter로 전해주는 형태로 Call Back!
