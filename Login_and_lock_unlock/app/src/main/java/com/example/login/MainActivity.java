package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private Button btn;
    private EditText email, pass;

    private static final String email_txt = "abs";
    private static final String pass_txt = "qwe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
    }

    public void login() {
        img = findViewById(R.id.img);
        btn = findViewById(R.id.btn);
        email = findViewById(R.id.text1);
        pass = findViewById(R.id.text2);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (String.valueOf(email.getText()).equals(email_txt) && String.valueOf(pass.getText()).equals(pass_txt)) {
                            img.setImageResource(R.mipmap.ic_unlock);
                        }
                        else {
                            img.setImageResource(R.mipmap.ic_block);
                        }

                    }
                }
        );
    }
}