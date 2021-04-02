# Kakao Track - Spring Framework

> 카카오 회사에서 웹을 기반한 서비스로 가장 많이 활용하고 있는 기술들을 배우며TDD(Test-Driven-Development)를 코딩 습관으로 녹이는 방법을 배우는 강좌.

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
        **Object Oriented** 

        - Encapsulation : 객체의 Field와 Method를 하나로 묶고, 실제 구현 내용 일부를 은닉
        - Abstraction : 객체의 설계도(이또한 class)를 만드는 것
        - Inheritance : 상위 클래스의 모든 멤버를 하위 클래스가 물려 받는 것, 재사용성 및 간결성 향상
        - Polymorphism : 여러 형태를 가질 수 있는 능력, 한 타입의 참조변수로 여러 타입의 객체를 참조
        ```

         

        **🌱 참고 - 2**

        ```
        **Object Oriented Design Principle - SOLID**

        - SRP(Single Responsibility Principle) - Class, Method는 한가지 책임만을 가진다.
        - OCP(Open/Close Principle) - 변경에는 닫혀있고, 확장에는 열려있어야 한다. 
        - LSP(Liskov Substitution Principle) - 자식 Object는 부모 Object로의 치환 가능하다.
        - ISP(Interface Segregation Principle) - Interface는 다양한 기능으로 분리해야 한다.
        - **DIP(Dependency Inversion Principle)** - Spring에서 다룰 내용
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
    **Design Pattern

    -** Template Method Pattern
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
