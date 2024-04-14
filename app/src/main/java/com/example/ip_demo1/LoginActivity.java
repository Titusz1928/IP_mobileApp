package com.example.ip_demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.LOGifcvLoginButton);
        Button buttonRegister = findViewById(R.id.LOGifcvRegisterButton);
        TextView buttonForgot = findViewById(R.id.LOGifcvForgotPasswordText);

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MenuActivityJ.class);
            startActivity(intent);
        });

        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        buttonForgot.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity1J.class);
            startActivity(intent);
        });
    }
}