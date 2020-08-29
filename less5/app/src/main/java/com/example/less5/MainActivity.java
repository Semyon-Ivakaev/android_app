package com.example.less5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText pass;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListennerOnButton(); // нужен тут чтобы слушал, когда нажмут на кнопку, мы не используем метод OnClick  у кнопку
    }

    // метод нужен для реализации всплывающего окношка при нажатии на кнопку
    public void addListennerOnButton() {
        pass = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.button);
        btn.setOnClickListener( // так делаем проверку, что нажали на нужную кнопку, у которой R.id.button
                new View.OnClickListener() { // без проверки это все работало бы со всем кнопками, на которые нажали!
                    @Override
                    public void onClick(View view) {
                        btn.setText("Done"); // при нажатии меняем надпись в кнопке
                        btn.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN)); // меняем он в кнопке на "красивый"
                        Toast.makeText(
                                MainActivity.this, pass.getText(),
                                Toast.LENGTH_SHORT // нужно для времени отображения окна ~ 2 сек.
                        ).show();
                    }
                }
        );
    }

}