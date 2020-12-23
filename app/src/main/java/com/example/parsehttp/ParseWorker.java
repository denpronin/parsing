package com.example.parsehttp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParseWorker {

    public void doParsing(String response, OnParseDoneListener callback) {
        Thread parsing = new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = Jsoup.parse(response);
                StringBuilder parsedText = new StringBuilder();
                parsedText.append("Title: " + doc.title()).append("\n");
                Elements links = doc.select("a");
                for (Element link : links) {
                    parsedText.append("Href: " + link.attr("href")).append("\n");
                }
                callback.onParseDone(parsedText.toString());
            }
        });
        parsing.start();
    }

    public interface OnParseDoneListener {
        void onParseDone(String parsedText);
    }
}
