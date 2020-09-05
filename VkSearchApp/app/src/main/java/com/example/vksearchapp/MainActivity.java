package com.example.vksearchapp;
/*
В манифест еще добаивть разрешение на доступ в интернет <uses-permission android:name="android.permission.INTERNET"/>
 */
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.vksearchapp.utils.NetworkUtils.generateURL;
import static com.example.vksearchapp.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    private TextView searchField;
    private Button searchButton;
    private TextView result;

    // делаем вложенный класс
    // 3 параметра: URL - то, что мы передаем; View - то, что таска нам вернет при выполнении;
    // String - то, что таска вернет после выполнения (json строку в данном случае)
    @SuppressLint("StaticFieldLeak") // эту аннотацию взял из коментов к 10 уроку, чтобы не было утечки памяти
    class VKQueryTask extends AsyncTask<URL, View, String> {

        @Override // этот метод сам прилетает при наследовании от AsyncTask
        protected String doInBackground(URL... urls) { // массив сюда передается, но мы передаем только 1 элемент
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);  //изначальный вид response = getResponseFromURL(generatedURL);

            } catch (IOException e) {                         // меняем на urls[0] так как у нас массив
                e.printStackTrace();                          // а в массив мы передаем только 1 элемент
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) { // в параметр передаем response
            String firstName = null;
            String lastName = null;
            try {
                JSONObject jsonResponce= new JSONObject(response); // для парсинга, иде просит трай/катч, делаем
                // массив находим в json по ключу "responce" {"response":[{"id":25630367,"first_name":"Alexander","last_name":"Kucherov","is_closed":false,"can_access_closed":true}]}
                JSONArray jsonArray = jsonResponce.getJSONArray("response");
                // responce имеет массив данных, в котором индекс 0 равен [{"id":25630367,"first_name":"Alexander","last_name":"Kucherov","is_closed":false,"can_access_closed":true}]
                JSONObject userInfo = jsonArray.getJSONObject(0);

                // зная значение ключа можно найти его значение "Alexander"
                firstName = userInfo.getString("first_name");
                lastName = userInfo.getString("last_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String resultingString = "Имя " + firstName + "\n" + "Фамилия " + lastName;

            result.setText(resultingString);                   // записываем в текстовую view
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_vk);
        result = findViewById(R.id.tv_result);

        View.OnClickListener OnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // в скобки передаем тот текст, который взяли из searchField и перевели его в строку
                URL generatedURL = generateURL(searchField.getText().toString()); // generateURL импортируем из utils
                new VKQueryTask().execute(generatedURL); // запуск второго потока с действиями
            }
        };
        searchButton.setOnClickListener(OnClickListener);
    }
}