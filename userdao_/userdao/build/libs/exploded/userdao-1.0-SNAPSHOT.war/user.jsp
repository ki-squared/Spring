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