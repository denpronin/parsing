package com.example.parsehttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ParsingActivity extends AppCompatActivity {
public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);
        Intent intent = getIntent();
        TextView parseText = (TextView) findViewById(R.id.parse_text);
        parseText.setMovementMethod(new ScrollingMovementMethod());
        parseText.setText(intent.getStringExtra(EXTRA_MESSAGE));
    }
}