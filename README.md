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

## 8. Annotation 기반 Tool

- Week - 9
    1. Lombok

        [https://projectlombok.org/setup/gradle](https://projectlombok.org/setup/gradle)

        ```java
        /* build.gradle */
        compileOnly 'org.projectlombok:lombok:1.18.20'
        annotationProcessor 'org.projectlombok:lombok:1.18.20'

        testCompileOnly 'org.projectlombok:lombok:1.18.20'
        testAnnotationProcessor 'org.projectlombok:lombok:1.18.20'
        ```

        외부 Access를 위한 Getter, Setter의 attribute은 왜 private으로 만들까?

        ⇒ 민감한 정보에 대한 보안을 위해(Getter에 보안장치 삽입 가능)

        Lombok은 필요하지만 많은 line을 차지하는 Getter, Setter에 대한 해결책?

        ```java
        package kr.ac.jejunu.userdao;

        import lombok.Getter;
        import lombok.Setter;

        @Getter
        @Setter
        public class User {
            private Integer id;
            private String name;
            private String password;
        }
        ```

        +) Preferences → Annotation Processors → Enable Annotation Processing

        ```java
        @Getter
        @Setter
        @Builder
        @NoArgsConstructor // 기본 생성자
        @AllArgsConstructor // 지금 존재하는 property에 대한 모든 생성자
        public class User {
            private Integer id;
            private String name;
            private String password;
        }
        ```

        ```java
        User user = User.builder().name(name).password(password).build();
        ```

        +) equals → hashcode에 대한 비교!

        - Object Hash Code, toString()에 대한 이야기



## 9. Spring 심화, Web Application

- Week - 9, 10, 11

    - ApplicationContext 계층구조 - 자식만이 부모의 Bean을 참조할 수 있는 구조
    - Bean Scope

        Singleton(Default), prototype, request, session, application, websocket

    - Lombok
    - Java Web Application

        → War, 구조

        ```
        WebContent
        		Meta-INF
        		WEB-INF
        			lib
        			views
        					repository.xml
        					servlet-context.xml
        					web.xml -> 웹 구동을 위한 정보
        ```

    → Sevlet

    ```java
    public void init(ServletConfig config) throws ServletException;
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;
    public void destroy();
    ```

    : WAS는 이미 Servlet을 구동할 수 있게 완료되어 있다. 

    : Sevlet 처음 로딩 시 Init() 구동 : 처음 request 시 한번만 수행

    : service()는 request 마다 한번씩 동작

    - 실습 - (구조)

        ```
        main
        		webapp
        				WEB-INF
        						web.xml
        ```

        → servlet API를 포함한 라이브러리

        : 'javax.servlet:javax.servlet-api:4.0.1'

        : plugin : id 'war'

    →  Filter

    ```java
    public void init(FilterConfig filterConfig) throws ServletException;
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;
    public void destroy();
    ```

    : Servlet 수행 전과 후에 공통적으로 수행될 logic이 있다면!

    : doFilter in → Servlet service → doFilter out

    → Listener

    ```
    javax.servlet.ServletContextListener : 컨텍스트 초기화, 마무리
    javax.servlet.ServletContextAttributeListener : 컨텍스트에 속성 추가, 제거
    javax.servlet.http.HttpSessionListener : 동시 사용자 얼마나?
    javax.servlet.ServletRequestListener : 요청 시 로그
    javax.servlet.ServletRequestAttributeListener : Request 속성 추가, 제거 수정 여부
    javax.servlet.http.HttpSessionBindingListener : 속성 객체가 세션에 바인딩 됐는지 제거 됐는지 여부
    ```

    : Listener (ContextInitialized) → Filter init → Servlet init → Filter doFilter in → ...

    : Servlet destroy → Filter destroy → Listener (ContextDestroyed)

    : Listener (ContextInitialized) → Filter init → Listener (Request Init) → Servlet init

    → JSP

    ```
    <%@ page import="org.springframework.context.ApplicationContext" %>
    <%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
    <%@ page import="kr.ac.jejunu.userdao.UserDao" %>
    <%@ page import="kr.ac.jejunu.userdao.User" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <%
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu.userdao");
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        User user = userDao.get(200);
    %>
    <html>
        <h1>
            Hello <%=user.getName()%>!!!
        </h1>
    </html>
    ```
    


## 10. Spring MVC

- Week - 11

    Servlet은 환경 관련 로직만 담당하고 JSP는 Servlet이 전달한 모델, Object에 있는 데이터만 꺼내서 브라우저로 전달해주는 Architecture → MVC

    → Spring MVC

    : DispatcherServlet

    : Life Cycle

    ```

    Client -> Dispatcher Servlet -> Handler Mapping
    -> Controller(HandlerAdapter) -> View Resolver
    ```

    : Spring에서는 MVC Controller에 해당하는 Dispatcher Servlet이 Client의 모든 요청을 받는다. 이 Dispatcher Servlet은 Handler Mapping을 통해서 Controller(특정 URL 실행)를 매핑, View Resolver가 물리적인 화면을 표현하여 이 매핑된 결과를 화면에 띄워주게 된다. 

    ```
    implementation "org.springframework:spring-webmvc:${springVersion}"
    ```

    : Handler Mapping → 어떤 Controller를 사용할 것인지!

    ```
    BeanNameUrlHandlerMapping(Default)
    SimpleUrlHandlerMapping
    RequestMethodHandlerMapping(Default) - 3.1
    DefaultAnnotationHandlerMapping(Deprecated)
    ```
    
    
    
    ## 11. SpringMVC - Resolver, View

- Week - 12
    - View Resolver

        : InternalResourceViewResolver .. → 각 프레임워크에 맞는 View Resolver를 제공해준다. 

    - Etc Resolver

        : view를 해석하는 resolver가 아니고, 예외가 발생했을 때 처리하는 resolver와 파일 업로드를 위한 resolver가 있는데, 파일을 받을 때(request에서) binary-file을 받을 수 있도록 하는 resolver!

        : HandlerExceptionResolver, MultipartResolver(TOMCAT 설정 주의!)

    - TOMCAT file upload 설정

        conf → content.xml

        ```
        <Context allowCasualMultipartParsing="true" path="/">
        ```

    - View

    - Interceptor(⇒ Spring의 Filter기능) → [**로그인**](https://cheershennah.tistory.com/107)

        : preHandle, postHandle, afterCompletion

    - Annotation-Driven ⇒ SpringMVC를 쓰면 무조건 넣어야 해!

    - 참고

        [https://cheershennah.tistory.com/107](https://cheershennah.tistory.com/107)
