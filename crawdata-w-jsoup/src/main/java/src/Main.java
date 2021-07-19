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
    public static String kid_word_url = "https://lingumi.com/topics/food";
    public static String cambrige_vi_url = "https://dictionary.cambridge.org/dictionary/learner-english/";

    // init kid sql helper
    public static DBKidEngSQLite dbKidEngSQLite = new DBKidEngSQLite();

    // init jsoup to cawl data
    public static JsoupMethod jsoupMethod = new JsoupMethod();

    // tag name of content will get
    public static String wordListTag = "audio.i-amphtml-fill-content";
    public static void main(String[] args) {
        // get list content from html tag
//        List<String> tagContentList = jsoupMethod.getTagContent(url, wordListTag);

        List<String> getWord = dbKidEngSQLite.getSingleColumn("word", "word");

        for (String wordItem : getWord) {
            String urlWithWord = cambrige_vi_url + wordItem;
            String word = wordItem;
            
            String tagContent = jsoupMethod.getSrcAudio(urlWithWord);
            
            System.out.println("word >>>: " + word);
            System.out.println("tagContent >>>: " + tagContent);
            dbKidEngSQLite.updateNewRowValue("https://dictionary.cambridge.org" + tagContent, word);
        }

    }

}
