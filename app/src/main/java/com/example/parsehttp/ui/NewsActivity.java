package com.example.parsehttp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
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
    public static final int SHARE = R.id.share;
    private String href;
    private TextView article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView title = (TextView) findViewById(R.id.titleNews);
        ImageView newsImageSrc = (ImageView) findViewById(R.id.newsImageSrc);
        article = (TextView) findViewById(R.id.article);
        Intent intent = getIntent();
        href = intent.getStringExtra(MESSAGE_HREF);
        title.setText(intent.getStringExtra(MESSAGE_TITLE));
        Picasso.get()
                .load(intent.getStringExtra(MESSAGE_IMG_SRC))
                .placeholder(R.drawable.ic_launcher_background)
                .into(newsImageSrc);
        ParseWorker parseWorker = new ParseWorker();
        parseWorker.doParsingNews(href, new ParseWorker.OnParseNewsDoneListener() {
            @Override
            public void  OnParseNewsDone(String newsText) {
                runOnUiThread(() -> {article.setText(newsText);}
                );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case SHARE:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, href);
                Intent chosenIntent = Intent.createChooser(intent, getString(R.string.chooser));
                startActivity(chosenIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}