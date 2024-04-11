package com.example.ip_demo1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ChangePasswordActivityJ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Button buttonBack = findViewById(R.id.CPAifcvBackPassword);
        Button buttonConfirm = findViewById(R.id.CPAifcvConfirmPassword);

        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(ChangePasswordActivityJ.this, Menu_Activity.class);
            startActivity(intent);
        });

        buttonConfirm.setOnClickListener(v -> {
            Intent intent = new Intent(ChangePasswordActivityJ.this, Menu_Activity.class);
            startActivity(intent);
        });
    }
}