package com.example.secondactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
Чтобы добавить второе окно нужно:
- перейти в res;
- правой кнопкой по папке layout;
- перейти в активити и выбрать (может быть ошибка при создании активити, см. урок №7 дударя);
- в файле манифеста скопировать из мейн активити <intent-filter> в секонд активити и произвести замены
у category и action, см.файл манифеста
- в новые активити добавить android:parentActivityName="com.example.secondactivity.MainActivity"
чтобы была стрелка "назад".
 */

public class MainActivity extends AppCompatActivity {
    private Button btn_go_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoNext();
    }

    public void btnGoNext() {
        btn_go_next = findViewById(R.id.btn_go_next);
        btn_go_next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // переход на другую страницу осуществялется через класс Intent
                        // в новом объекте указываем путь, где лежит нужный класс
                        Intent secondAct = new Intent("com.example.secondactivity.SecondActivity");
                        startActivity(secondAct); // выполнение перехода
                    }
                }
        );
    }
}