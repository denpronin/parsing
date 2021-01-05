package com.example.parsehttp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parsehttp.R;
import com.example.parsehttp.model.News;
import com.example.parsehttp.network.ParseWorker;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsActivity extends AppCompatActivity {
    public static final String MESSAGE_HREF = "href";
    public static final String MESSAGE_TITLE = "title";
    public static final String MESSAGE_IMG_SRC = "imgSrc";
    private TextView article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        TextView title = (TextView) findViewById(R.id.titleNews);
        ImageView newsImageSrc = (ImageView) findViewById(R.id.newsImageSrc);
        article = (TextView) findViewById(R.id.article);
        Intent intent = getIntent();
        title.setText(intent.getStringExtra(MESSAGE_TITLE));
        Picasso.get()
                .load(intent.getStringExtra(MESSAGE_IMG_SRC))
                .placeholder(R.drawable.ic_launcher_background)
                .into(newsImageSrc);
        ParseWorker parseWorker = new ParseWorker();
        parseWorker.doParsingNews(intent.getStringExtra(MESSAGE_HREF), new ParseWorker.OnParseNewsDoneListener() {
            @Override
            public void  OnParseNewsDone(String newsText) {
                runOnUiThread(() -> {article.setText(newsText);}
                );
            }
        });
    }


}