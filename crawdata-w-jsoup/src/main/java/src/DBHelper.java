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
public class DBHelper {
     public Connection connect() {
        String dbName = "english-vocab-html-raw.db";
        String url = "jdbc:sqlite:/home/vietnguyenw/vietnguyenhoangw/java/CrawDataWithJsoup/crawdata-w-jsoup/src/main/java/assets/";
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url + dbName);
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return conn;
    }

    // return the word
    // we can using word to find the vietnames mean or using that like a key
     
    // get data by column name in table and return a list
    public ArrayList<String> selectAll(String column, String table){
        
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT " + column +  " FROM " + table;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                list.add(rs.getString("word"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Insert a new row into the warehouses table
     *
     * @param aip
     * @param howToUse
     * @param word
     */
    public Boolean insert(String word, String htmlRaw) {
        Boolean success;
        String sql = "UPDATE vi_word_copy SET html_raw = (?) WHERE word LIKE (?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, htmlRaw);
            pstmt.setString(2, word);
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
