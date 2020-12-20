package com.example.parsehttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        editText = (EditText) findViewById(R.id.url_text);
        textView = (TextView) findViewById(R.id.request_text);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText("");
        Thread request = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(editText.getText().toString());
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    InputStreamReader inputStream = new InputStreamReader(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStream);
                    CharSequence line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        textView.append(line + "\n");
                    }
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        request.start();
    }
}