package com.example.ip_demo1.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ip_demo1.model.UserSession;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if the user is logged in
        UserSession userSession = UserSession.getInstance(this);
        if (userSession.getUser() != null) {
            // If the user is logged in, navigate to MainActivity
            Intent intent = new Intent(this, MenuActivityJ.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Log.d("MyTag","user is logged in");
            startActivity(intent);
        } else {
            // If no user is logged in, navigate to LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Log.d("MyTag","user is not logged in");
            startActivity(intent);
        }

        // Finish this activity
        finish();
    }
}
