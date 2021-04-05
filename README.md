# Kakao Track - Spring Framework

> 카카오 회사에서 웹을 기반한 서비스로 가장 많이 활용하고 있는 기술들을 배우며,
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

        ![Kakao%20Track%20-%20Spring%20Framework%206c7bc6428d8e4193b978a2ea8c0fe6cd/Untitled.png](Kakao%20Track%20-%20Spring%20Framework%206c7bc6428d8e4193b978a2ea8c0fe6cd/Untitled.png)

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
        
 
