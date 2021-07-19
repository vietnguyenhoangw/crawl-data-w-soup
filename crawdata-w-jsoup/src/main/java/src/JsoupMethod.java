/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;
import java.util.List;
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

    // get content of tag (ex: <div class="demo">Content of tag</div>)
    // in this example the tagmane input is >>>>>> div.demo
    public List<String> getListTagContent(String url, String tagName) {
        List<String> tagContentList = new ArrayList<String>();
        try {
            Document doc = Jsoup.connect(url.toString()).get();
            Elements divs = doc.select(tagName);

            for (Element item : divs) {
                tagContentList.add(item.text());
            }
        } catch (Exception e) {
            System.out.println("Error when get content of tag: " + e);
        }

        return tagContentList;
    }

    public String getSingleTagContent(String url, String tagName) {
        String tagContent = "";
        try {
            Document doc = Jsoup.connect(url.toString()).get();
            Elements divs = doc.select(tagName);
            tagContent = divs.get(0).text();
        } catch (Exception e) {
            System.out.println("Error when get content of tag: " + e);
        }

        return tagContent;
    }

    public String getSrcAudio(String url) {
        String tagContent = "";
        try {
            Document doc = Jsoup.connect(url.toString()).get();
            String src = doc.select("source").first().attr("src");
            tagContent = src;
        } catch (Exception e) {
            System.out.println("Error when get content of tag: " + e);
        }

        return tagContent;
    }
}
