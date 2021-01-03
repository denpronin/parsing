package com.example.parsehttp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString(RESPONSE_TEXT, textResponse.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //textResponse.setText(savedInstanceState.getString(RESPONSE_TEXT, "000"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parseWorker = new ParseWorker();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter();
        parseWorker.doParsing(URL_FOR_PARSING, new ParseWorker.OnParseDoneListener() {
            @Override
            public void  onParseDone(List<News> newsList) {
                runOnUiThread(() -> {newsAdapter.setItems(newsList);}
                );
            }
        });
        recyclerView.setAdapter(newsAdapter);
    }
}