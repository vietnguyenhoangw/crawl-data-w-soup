/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author vietnguyenw
 */
public class DBHelperMySQL {

    private static String DB_URL = "jdbc:mysql://localhost:3306/english_vocab?autoReconnect=true&useSSL=false";
    private static String USER_NAME = "root";
    private static String PASSWORD = "Hoangviet2@";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    // return the word
    // we can using word to find the vietnames mean or using that like a key
    // get data by column name in table and return a list

    public ArrayList<String> selectAll(String column, String table) {
        return new ArrayList();
    }

}
