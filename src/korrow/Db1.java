/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korrow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kantarat
 */
public class Db1 {
    public static Connection getConnectionBuilder() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://korrow.sit.kmutt.ac.th:3306/Project?zeroDateTimeBehavior=convertToNull&autoReconnect=true&useUnicode=true&characterEncoding="
                + "UTF-8&characterSetResults=utf8&connectionCollation=utf8_general_ci";
       String user = "sitfreshy";
       String password = "123456789";
       Connection connction = DriverManager.getConnection(url, user, password);
        /*Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://35.166.222.133:3306/Project? "+
                "sitfreshy", "123456789" );*/
        return connction;
    }
}
