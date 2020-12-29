package com.example.parsehttp;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RequestWorker {

    private static final String TAG = "textURL.getText().toString()";

    public void doRequest(String strUrl, OnRequestDoneListener callback) {

        Thread request = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection urlConnection;
                BufferedReader reader = null;
                InputStreamReader inputStream = null;
                try {
                    URL url = new URL(strUrl);
                    urlConnection = (HttpsURLConnection) url.openConnection();
                    inputStream = new InputStreamReader(urlConnection.getInputStream());
                    reader = new BufferedReader(inputStream);
                    CharSequence line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        Log.d(TAG, line.toString());
                        response.append(line).append("\n");
                    }
                    callback.onRequestDone(response.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.d(TAG, "!!! Error on append response !!!");
                } finally {
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

    public interface OnRequestDoneListener {
        void onRequestDone(String response);
    }
}
