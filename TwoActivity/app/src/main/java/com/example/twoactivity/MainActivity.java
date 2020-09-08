package com.example.twoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText textEntry;
    private Button changeActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeActivityButton = findViewById(R.id.b_change_activity);
        textEntry = findViewById(R.id.et_text_entry);

        changeActivityButton.setOnClickListener(new View.OnClickListener() {
            /*
            При нажатии на кнопку создаем интент, в котором указываем, что назначение
            ChildActivity
             */
            @Override
            public void onClick(View view) {
                String textEntered = textEntry.getText().toString();
                Context context = MainActivity.this;

                Class destinationActivity = ChildActivity.class;

                Intent childActivityIntent = new Intent(context, destinationActivity);
                // в интент вкладываем текст, который хотим передать в дочерний активити через Intent
                // ключ "entered_text" можно любой childActivityIntent.putExtra("entered_text", textEntered);
                // но правильней передавать константу из Intent Intent.EXTRA_TEXT
                childActivityIntent.putExtra(Intent.EXTRA_TEXT, textEntered);

                startActivity(childActivityIntent);
            }
        });
    }
}