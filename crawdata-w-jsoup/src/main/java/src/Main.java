/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.List;

/**
 *
 * @author vietnguyenw
 */
public class Main {

    public static String url = "https://lingumi.com/topics/animals";

    public static JsoupMethod jsoupMethod = new JsoupMethod();

    public static String wordListTag = "div.soundwall_unit_text";

    public static void main(String[] args) {
        List<String> tagContentList = jsoupMethod.getTagContent(url, wordListTag);

        for (String item : tagContentList) {
            System.out.println("item > " + item);
        }
    }

}
