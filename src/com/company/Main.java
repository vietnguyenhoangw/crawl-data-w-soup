package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static DBHelper dbHelper;
    public static void main(String[] args) throws IOException {
        dbHelper = new DBHelper();
        Main main = new Main();
        ArrayList<String> arraylist = dbHelper.selectAll();
        ArrayList<String> arraylistIAP = new ArrayList<>();

        for (int i = 648; i < arraylist.size(); i++) {
            String url = "https://dictionary.cambridge.org/dictionary/english/";
            url += arraylist.get(i);
            dbHelper.insert(arraylist.get(i), main.getAIP(url), main.getUseWay(url));
            System.out.println(i + " - " + arraylist.get(i) + " - " + main.getAIP(url) + main.getUseWay(url));
        }

        System.out.println(arraylist.size());
        System.out.println(arraylistIAP.size());
    }

    public String getAIP(String url) throws IOException {
        String linkText = "";
        try {
            Document doc = Jsoup.connect(url + "").get();
            Element masthead = doc.select("span.pron.dpron").first();
            try {
                linkText = masthead.text();
            } catch (Exception e) {
                linkText = "";
            }
        } catch (Exception e) {
            System.out.println(e);
            linkText = "";
        }
        return linkText;
    }

    public String getUseWay(String url) throws IOException {
        String linkText = "";
        try {
            Document doc = Jsoup.connect(url + "").get();
            Element masthead = doc.select("div.examp.dexamp").first();
            try {
                linkText = masthead.text();
            } catch (Exception e) {
                linkText = "";
            }
        } catch (Exception e) {
            System.out.println(e);
            linkText = "";
        }
        return linkText;
    }
}
