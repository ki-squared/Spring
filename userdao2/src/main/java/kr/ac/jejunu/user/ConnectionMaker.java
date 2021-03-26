package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
     public Connection getConnection() throws ClassNotFoundException, SQLException;
//
//    {
//        // Where is the data stored? MySQL
//
//        // Load Driver
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        // Make Connection
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost/kakao?" +
//                        "characterEncoding=utf-8&serverTimezone=UTC"
//                , "root", "root"
//        );
//    }
}