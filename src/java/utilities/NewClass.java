/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Owner-PC
 */
public class NewClass {

 private static Connection con;
static Statement stmt;
private static final String url = "jdbc:oracle:thin:@144.217.163.57:1521:XE";
private static final String driverName = "oracle.jdbc.OracleDriver";
private static final String username = "finalp1";
private static final String password = "finalp1";
 public static Connection getConnection() throws SQLException {
 try {
 Class.forName(driverName);
 con = DriverManager.getConnection(url, username, password);
}
catch (ClassNotFoundException ex) {
 System.out.println("Driver not found.");
 }
 return con;
 }
public ResultSet rss(String a) throws SQLException {
stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
return stmt.executeQuery(a);
       
}
}
