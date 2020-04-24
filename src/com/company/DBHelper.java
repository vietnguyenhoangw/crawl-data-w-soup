package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper {
    public Connection connect() {
        String url = "jdbc:sqlite:/home/vietnguyenw/Vietnguyenhoangw/Home/JAVA/Test_sql/src/asset/englishvocab.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return conn;
    }

    public ArrayList<String> selectAll(){
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT word FROM vi_word";

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
    public Boolean insert(String word, String aip, String howToUse) {
        aip += "";
        howToUse += "";
        Boolean success;
        String sql = "UPDATE vi_word SET aip = (?), how_to_use = (?)" +
                "WHERE word LIKE (?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, aip);
            pstmt.setString(2, howToUse);
            pstmt.setString(3, word);
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
