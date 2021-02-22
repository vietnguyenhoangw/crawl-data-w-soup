/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author vietnguyenw
 */
public class Main {

    public static String url = "https://dictionary.cambridge.org/dictionary/english/";

    public static DBHelper dbHelper;
    public static JsoupMethod jsoupMethod;

    public static String wordInList = "";
    public static String wordFromUrl = "";
    
    /*
        CRAW DATA TAG NAME
    */
    
    // sound
    public static String soundName = "source";
    
    // word
    public static String exampleList = "ul.hul-u.hul-u0.ca_b.daccord_b";

    public static void main(String[] args) {

        dbHelper = new DBHelper();
        jsoupMethod = new JsoupMethod();

        ArrayList<String> arraylist = dbHelper.selectAll("word", "vi_word_copy");

        for (int i = 648; i < arraylist.size(); i++) {
            String word = arraylist.get(i);
            String newUrl = url + word;
            
            // custom this getRawLiDataFromULTag to get data you want !!!
            String rawHtmlData = jsoupMethod.getRawLiDataFromULTag(newUrl, 
                    arraylist.get(i), soundName);
            
            System.out.println(">>>>>: " + rawHtmlData);
//            dbHelper.insert(word, rawHtmlData);
        }
    }

}
