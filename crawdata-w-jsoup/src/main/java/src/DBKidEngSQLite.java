/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Word;

/**
 *
 * @author vietnguyenw
 */
public class DBKidEngSQLite {

    public Connection connect() {
        String dbName = "kid-english.db";

        String url = "jdbc:sqlite:/home/vietnguyenw/vietnguyenhoangw/java/CrawDataWithJsoup/crawdata-w-jsoup/src/main/java/assets/";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url + dbName);
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return conn;
    }
    
    public int countDataInTable(String table) {
        int numOfData = 0;
        String sql = "SELECT COUNT(*) as total FROM word";
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                numOfData = Integer.parseInt(rs.getString("total"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return numOfData;
    }
    
        /**
     * Insert a new row into the warehouses table
     */
    public Boolean insertWord(int id, String word, int topicId) {
        Boolean success;
        String sql = "INSERT INTO word (\"id\", \"word\", \"topic_id\") VALUES ((?), (?), (?))";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, word);
            pstmt.setInt(3, topicId);
            pstmt.executeUpdate();
            success = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            success = false;
        }
        System.out.println("insert status: " + success);
        return success;
    }

}
