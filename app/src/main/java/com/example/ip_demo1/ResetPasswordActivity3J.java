package com.example.ip_demo1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity3J extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password3);

        Button buttonBack = findViewById(R.id.RPA3ifcvBackButton);
        Button buttonConfirm = findViewById(R.id.RPA3ifcvConfirmButton);

        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(ResetPasswordActivity3J.this, MainActivityJ.class);
            startActivity(intent);
        });

        buttonConfirm.setOnClickListener(v -> {
            Intent intent = new Intent(ResetPasswordActivity3J.this, MainActivityJ.class);
            startActivity(intent);
        });
    }
}