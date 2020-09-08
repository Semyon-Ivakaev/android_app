package com.example.twoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        textView = findViewById(R.id.tv_display);
        // вызов Intenta как прием интента из MainActivity
        Intent intentThatStartedThisActivity = getIntent();
        // проверяем, что в переданом интенте есть данные с ключом Intent.EXTRA_TEXT
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT) ) {
            // если есть, то в переменную достаем эту строку по ключу
            String textEntered = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);

            textView.setText(textEntered);
        }
    }
}