package com.example.testwithdudar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText obj1, obj2;
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obj1 = findViewById(R.id.num1);
        obj2 = findViewById(R.id.num2);
        res = findViewById(R.id.result);

    }

    public void clickOnPlus(View v) {
        int num1 = Integer.parseInt(obj1.getText().toString());
        int num2 = Integer.parseInt(obj2.getText().toString());
        int result = num1 + num2;
        res.setText(Integer.toString(result));
    }
}