/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.*;
import java.util.ArrayList;
import models.Level;
import models.Topic;
import models.Word;

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

    // get all words from db
    public ArrayList<String> getAllWord(Connection conn) {
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM words_vi");
            while (rs.next()) {
                System.out.println(">>>>> rs: " + rs.getString("aip"));
            }
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return new ArrayList();
    }

    // insert 'words_vi'
    public void insertWords(Connection conn, Word word) {
        try {
            try {
                String sqlCmd = "INSERT INTO words_vi( id, mean , word, level_type, topic_id, aip, how_to_use, example) VALUES ((?), (?), (?), (?), (?),(?), (?), (?))";
                PreparedStatement ppstmt = conn.prepareStatement(sqlCmd);
                ppstmt.setInt(1, word.getId());
                ppstmt.setString(2, word.getMean());
                ppstmt.setString(3, word.getWord());
                ppstmt.setInt(4, word.getLevel_type());
                ppstmt.setInt(5, word.getTopic_id());
                ppstmt.setString(6, word.getAip());
                ppstmt.setString(7, word.getHow_to_use());
                ppstmt.setString(8, word.getExample());
                ppstmt.execute();
                System.out.println(word.getMean() + " >> insert words success!");
            } catch (Exception ex) {
                System.out.println("insert words fail!");
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println("insert words fail!");
            ex.printStackTrace();
        }
    }

    public void insertTopics(Connection conn, Topic topic) {
        try {
            try {
                String sqlCmd = "INSERT INTO topics( id, topic_name_vi , topic_name_eng) VALUES ((?), (?), (?))";
                PreparedStatement ppstmt = conn.prepareStatement(sqlCmd);
                ppstmt.setInt(1, topic.getId());
                ppstmt.setString(2, topic.getTopic_name_eng());
                ppstmt.setString(3, topic.getTopic_name_vi());
                ppstmt.execute();
                System.out.println(topic.getTopic_name_eng() + " >> insert topic success!");
            } catch (Exception ex) {
                System.out.println("insert topic fail!");
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println("insert topic fail!");
            ex.printStackTrace();
        }
    }

    public void insertLevel(Connection conn, Level level) {
        try {
            try {
                String sqlCmd = "INSERT INTO levels( id, level_type , level_name) VALUES ((?), (?), (?))";
                PreparedStatement ppstmt = conn.prepareStatement(sqlCmd);
                ppstmt.setInt(1, level.getId());
                ppstmt.setInt(2, level.getLevel_type());
                ppstmt.setString(3, level.getLevel_name());
                ppstmt.execute();
                System.out.println(level.getLevel_name() + " >> insert level success!");
            } catch (Exception ex) {
                System.out.println("insert level fail!");
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println("insert level fail!");
            ex.printStackTrace();
        }
    }
}
