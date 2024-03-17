package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val button=findViewById<Button>(R.id.btBack)

        button.setOnClickListener {
            val intent = Intent(this, SettingsFragment::class.java)
            startActivity(intent)
        }

    }
}