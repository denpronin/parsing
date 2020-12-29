package com.example.parsehttp;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseWorker {
    private static final String TAG = "textURL.getText().toString()";
    private StringBuilder parsedText;

    public void doParsing(String response, OnParseDoneListener callback) {
        Thread parsing = new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc;
                try {
                doc = Jsoup.connect(response).userAgent("Mozila")
                        .referrer("http://www.google.com")
                        .timeout(60000)
                        .get();
                    parsedText = new StringBuilder();
                    parsedText.append("Title: ").append(doc.title()).append("\n");
                    Elements news = doc.select("ul.b-lists-category");
                    Elements newsItems = news.select("li.lists_category__li");

                    for (Element newsItem : newsItems) {
                        parsedText.append("Title: ").append(newsItem.select("a").attr("title")).append("\n");
                        parsedText.append("Href: ").append(newsItem.select("a").attr("href")).append("\n");
                        parsedText.append("Image: ").append(newsItem.select("img").attr("src")).append("\n");
                        parsedText.append("Date: ").append(newsItem.select("p[class=category__date]").text()).append("\n");
                        parsedText.append("Note: ").append(newsItem.select("p").get(1).text()).append("\n");
                        parsedText.append("\n").append("\n");
                    }
                    callback.onParseDone(parsedText.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.d(TAG, "!!! Error on append response !!!");
                }
            }
        });
        parsing.start();
    }

    public interface OnParseDoneListener {
        void onParseDone(String parsedText);
    }
}
