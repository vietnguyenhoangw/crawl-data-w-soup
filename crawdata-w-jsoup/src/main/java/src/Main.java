/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author vietnguyenw
 */
public class Main {

    // web data
    public static String url = "https://lingumi.com/topics/food";

    // init kid sql helper
    public static DBKidEngSQLite dbKidEngSQLite = new DBKidEngSQLite();

    // init jsoup to cawl data
    public static JsoupMethod jsoupMethod = new JsoupMethod();

    // tag name of content will get
    public static String wordListTag = "div.soundwall_unit_text";

    public static void main(String[] args) {
        // get list content from html tag
        List<String> tagContentList = jsoupMethod.getTagContent(url, wordListTag);

        for (int i = 0; i < tagContentList.size(); i++) {
            int totalData = dbKidEngSQLite.countDataInTable("word");
            int id = totalData + i;
            Boolean insertToDb = dbKidEngSQLite.insertWord(id, tagContentList.get(i), 4);
            System.out.println("Is insert success: " + insertToDb);
        }
    }

}
