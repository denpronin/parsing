package com.example.parsehttp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ParsingActivity extends AppCompatActivity {
//public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);
       // ImageView imageView = (ImageView) findViewById(R.id.imageSrc);
/*        Picasso.get()
                .load("https://img.tyt.by/390x260c/n/afisha/00/f/vanya_usovich_urgant.jpg")
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);//Your image view*/
        Intent intent = getIntent();
        initRecyclerView();
        /*TextView parseText = (TextView) findViewById(R.id.parse_text);
        parseText.setMovementMethod(new ScrollingMovementMethod());
        parseText.setText(intent.getStringExtra(EXTRA_MESSAGE));*/
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter();
        for (int i = 1; i <= 10; i++) {
            newsAdapter.newsList.add(new News("Хочется верить, что в моей родной стране добро победит. Ваня Усович впервые пришел к Урганту",
                    "",
                    "https://img.tyt.by/390x260c/n/afisha/00/f/vanya_usovich_urgant.jpg",
                    "Комик родом из Беларуси Ваня Усович впервые пришел на шоу Вечерний Ургант. Он рассказал, каким был для него этот год, и выразил надежду на то, что в следующем году в Беларуси все наладится и добро победит.",
                    "26 декабря 2020"));
        }
        recyclerView.setAdapter(newsAdapter);
    }
}