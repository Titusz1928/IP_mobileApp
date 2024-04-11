package com.example.ip_demo1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActualizareActivityJ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizare_j);

        Button saveButton = findViewById(R.id.ACTbcvSaveButton);

        saveButton.setOnClickListener(v -> {
            Intent intent = new Intent(ActualizareActivityJ.this, MenuActivityJ.class);
            startActivity(intent);
        });
    }
}
