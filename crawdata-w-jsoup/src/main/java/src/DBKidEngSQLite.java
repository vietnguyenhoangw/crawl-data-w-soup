/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vietnguyenw
 */
public class DBKidEngSQLite {

    public Connection connect() {
        String dbName = "kid-enlish.db";

        String url = "jdbc:sqlite:/home/vietnguyenw/vietnguyenhoangw/java/CrawDataWithJsoup/crawdata-w-jsoup/src/main/java/assets/";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url + dbName);
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return conn;
    }
}
