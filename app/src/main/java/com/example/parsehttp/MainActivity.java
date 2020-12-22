package com.example.parsehttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private EditText textURL;
    private TextView textResponse;
    private static final String TAG = "MainActivity";
    public static final String RESPONSE_TEXT = "textResponse";

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

    }

    public void onButtonClick(View view) {


        textResponse.setText("");

        Thread request = new Thread(new Runnable() {
            @Override
            public void run() {
                /*try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                HttpsURLConnection urlConnection;
                BufferedReader reader = null;
                InputStreamReader inputStream = null;
                try {
                    URL url = new URL(textURL.getText().toString());
                    urlConnection = (HttpsURLConnection) url.openConnection();
                    inputStream = new InputStreamReader(urlConnection.getInputStream());
                    reader = new BufferedReader(inputStream);
                    CharSequence line;
                    while ((line = reader.readLine()) != null) {
                        Log.d(TAG, line.toString());
                        textResponse.append(line + "\n");
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (reader != null) {

                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        });
        request.start();
    }
}