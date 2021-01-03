package com.example.parsehttp;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseWorker {
    private static final String TAG = "textURL.getText().toString()";
    private static final String USER_AGENT = "Chrome/Opera";
    private static final String REFERRER = "http://www.google.com";
    private static final int TIMEOUT = 60000;
    private List<News> newsList;

    public void doParsing(String response, OnParseDoneListener callback) {
        Thread parsing = new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc;
                try {
                doc = Jsoup.connect(response).userAgent(USER_AGENT)
                        .referrer(REFERRER)
                        .timeout(TIMEOUT)
                        .get();
                    Elements news = doc.select("ul.b-lists-category");
                    Elements newsItems = news.select("li.lists_category__li");
                    newsList = new ArrayList<>();
                    for (Element newsItem : newsItems) {
                        newsList.add(new News(
                        newsItem.select("a").attr("title"),
                        newsItem.select("a").attr("href"),
                        newsItem.select("img").attr("src"),
                        newsItem.select("p").get(1).text(),
                        newsItem.select("p[class=category__date]").text()
                        ));
                    }
                    callback.onParseDone(newsList);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.d(TAG, "!!! Error on append response !!!");
                }
            }
        });
        parsing.start();
    }

    public interface OnParseDoneListener {
        void onParseDone(List<News> newsList);
    }
}
