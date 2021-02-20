/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author vietnguyenw
 */
public class JsoupMethod {

    // get list li html in ul tag
    
    // from url param
    // get tag name address "ul.hul-u.hul-u0.ca_b.daccord_b"
    // from word import
    
    public String getRawLiDataFromULTag(String url, String word, String tagName) {
        String returnString = "";
        try {
            Document doc = Jsoup.connect(url.toString()).get();
            Elements rawHtmlList = Jsoup.connect(url)
                    .get()
                    .select(tagName);
            try {
                // check if list have data
                if (rawHtmlList.size() > 0) {
                    for (Element rawHtml : rawHtmlList) {
                        System.out.println("\n\n\n>>>>>>>>> " + word + ">>> " + rawHtml);
                        returnString = rawHtml.toString();
                    }
                } else {
                    System.out.println("<<<<<<<< " + word + " >>> Dont have data >>>>>>");
                }

            } catch (Exception e) {
                returnString = "";
            }
        } catch (Exception e) {
            System.out.println(e);
            returnString = "";
        }

        return returnString;
    }
}
