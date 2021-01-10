package com.example.parsehttp.network;

import android.text.format.DateUtils;
import android.util.Log;

import com.example.parsehttp.model.News;

import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseWorker {
    private static final String TAG = "ParseWorker";
    private static final String USER_AGENT = HttpConnection.DEFAULT_UA;
    private static final String REFERRER = "http://www.google.com";
    private static final int TIMEOUT = (int) DateUtils.MINUTE_IN_MILLIS;
    private List<News> newsList;
    private StringBuilder newsText;

    public void doParsingNewsList(String response, OnParseNewsListDoneListener callback) {
        Thread parsing = new Thread(() -> {
            try {
                Document doc = Jsoup.connect(response).userAgent(USER_AGENT)
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
                            newsItem.select("p").size() > 1 ? newsItem.select("p").get(1).text() : "No description",
                            newsItem.select("p[class=category__date]").text()
                    ));
                }
                callback.OnParseNewsListDone(newsList);
            } catch (IOException ex) {
                ex.printStackTrace();
                Log.d(TAG, "!!! Error on append response !!!");
            }
        });
        parsing.start();
    }

    public interface OnParseNewsListDoneListener {
        void OnParseNewsListDone(List<News> newsList);
    }

    public void doParsingNews(String response, OnParseNewsDoneListener callback) {
        Thread parsing = new Thread(() -> {
            try {
                Document doc = Jsoup.connect(response).userAgent(USER_AGENT)
                        .referrer(REFERRER)
                        .timeout(TIMEOUT)
                        .get();
                Elements news = doc.select("[class=b-article]");
                Elements p = news.select("p");
                int pSize = p.size();
                newsText = new StringBuilder();
                if (pSize != 0) {
                    for (int i = 0; i < pSize; i++) {
                        newsText.append(p.get(i).text()).append("\n");
                        newsText.append("\n");
                    }
                }
                callback.OnParseNewsDone(newsText.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                Log.d(TAG, "!!! Error on append response !!!");
            }
        });
        parsing.start();
    }

    public interface OnParseNewsDoneListener {
        void OnParseNewsDone(String newsText);
    }
}
