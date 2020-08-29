package com.example.less5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText pass;
    private Button btn, btn2;
    private RatingBar rating; // какую оценку поставил пользователь
    private TextView review; // текст, который выводим

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListennerOnButton(); // нужен тут чтобы слушал, когда нажмут на кнопку, мы не используем метод OnClick  у кнопку
    }

    public void addListennerReview() {

    }

    // метод нужен для реализации всплывающего окношка при нажатии на кнопку
    public void addListennerOnButton() {
        pass = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
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

        rating = findViewById(R.id.ratingBar);
        review = findViewById(R.id.review);

        rating.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    // float v нужна для оценки, туда пишет значения. boolean b нужен для определения
                    // кем выполнена оценка: вручную пользователем или при выполнении программы
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        review.setText("Оценка " + String.valueOf(v));
                    }
                }
        );
        btn2.setOnClickListener( // так делаем проверку, что нажали на нужную кнопку, у которой R.id.button
                new View.OnClickListener() { // без проверки это все работало бы со всем кнопками, на которые нажали!
                    @Override
                    public void onClick(View view) {
                        // добавляем alert
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                        a_builder.setMessage("Закрыть?") // выводим сообещние
                                .setCancelable(false) // чтобы не закрывалось сразу
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish(); // закрываем приложение
                                    }
                                })
                                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel(); // закрываем alert
                                    }
                                });
                        // это уже сам запуск окна, выше его внутренности (то что есть у алерта)
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Закрытие программы"); // название алерта
                        alert.show(); // показ алерта
                    }
                }
        );
    }

}