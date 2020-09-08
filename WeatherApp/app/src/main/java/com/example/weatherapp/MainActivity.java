package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.weatherapp.utils.NetworkUtils.generateURL;
import static com.example.weatherapp.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchButton;
    private TextView result;


    @SuppressLint("StaticFieldLeak")
    class WFtask extends AsyncTask<URL, View, String> {
        @Override
        protected String doInBackground(URL...urls) {
            String responce = null;
            try {
                responce = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responce;
        }

        @Override
        protected void onPostExecute(String responce) {
            double minTemp = 0;
            double maxTemp = 0;

            try {
                JSONObject jsonResponce = new JSONObject(responce);

                //JSONArray jsonArray = jsonResponce.getJSONArray("main"); в ответе не массив, а просто объект
                JSONObject tempInfo = jsonResponce.getJSONObject("main");
                minTemp = tempInfo.getDouble("temp_min") - 273.15;
                maxTemp = tempInfo.getDouble("temp_max") - 273.15;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String resultingString = "Минимальные температура: " + String.format("%.2f", minTemp) + "\n" +
                    "Максимальная температура: " + String.format("%.2f", maxTemp);

            result.setText(resultingString);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.et_search_field);
        searchButton = findViewById(R.id.b_search_button);
        result = findViewById(R.id.tv_result);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = searchField.getText().toString();
                URL generateURL = generateURL(text);
                new WFtask().execute(generateURL);
            }
        };
        searchButton.setOnClickListener(onClickListener);
    }
}