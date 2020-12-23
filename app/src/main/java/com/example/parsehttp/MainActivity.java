package com.example.parsehttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText textURL;
    private TextView textResponse;
    private static final String TAG = "MainActivity";
    public static final String RESPONSE_TEXT = "textResponse";
    private RequestWorker requestWorker;
    private ParseWorker parseWorker;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESPONSE_TEXT, textResponse.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textResponse.setText(savedInstanceState.getString(RESPONSE_TEXT, "000"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textURL = (EditText) findViewById(R.id.url_text);
        textResponse = (TextView) findViewById(R.id.request_text);
        textResponse.setMovementMethod(new ScrollingMovementMethod());
        requestWorker = new RequestWorker();
        parseWorker = new ParseWorker();
    }

    public void onButtonClick(View view) {
        requestWorker.doRequest(textURL.getText().toString(), new RequestWorker.OnRequestDoneListener() {
            @Override
            public void onRequestDone(String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textResponse.setText(response);
                    }
                });
            }
        });
    }

    public void onParseButtonClick(View view) {
        parseWorker.doParsing(textResponse.getText().toString(), new ParseWorker.OnParseDoneListener() {
            @Override
            public void onParseDone(String parsedText) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, ParsingActivity.class);
                        intent.putExtra(ParsingActivity.EXTRA_MESSAGE, parsedText);
                        startActivity(intent);
                    }
                });

            }
        });
    }
}