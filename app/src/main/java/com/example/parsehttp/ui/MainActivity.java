package com.example.parsehttp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import com.example.parsehttp.R;
import com.example.parsehttp.network.ParseWorker;
import com.example.parsehttp.model.News;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String RESPONSE_TEXT = "textResponse";
    private static final String URL_FOR_PARSING = "https://afisha.tut.by/news/";
    private ParseWorker parseWorker;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseWorker = new ParseWorker();
        initRecyclerView();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewsAdapter.OnNewsClickListener onNewsClick = new NewsAdapter.OnNewsClickListener() {
            @Override
            public void onNewsClick(News news) {
                runOnUiThread(() -> {
                    Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                    intent.putExtra(NewsActivity.MESSAGE_TITLE, news.getTitle());
                    intent.putExtra(NewsActivity.MESSAGE_IMG_SRC, news.getImageSrc());
                    intent.putExtra(NewsActivity.MESSAGE_HREF, news.getHref());
                    startActivity(intent);}
                );
            }
        };
        newsAdapter = new NewsAdapter(onNewsClick);
        parseWorker.doParsingNewsList(URL_FOR_PARSING, new ParseWorker.OnParseNewsListDoneListener() {
            @Override
            public void  OnParseNewsListDone(List<News> newsList) {
                runOnUiThread(() -> {newsAdapter.setItems(newsList);}
                );
            }
        });

        recyclerView.setAdapter(newsAdapter);
    }
}