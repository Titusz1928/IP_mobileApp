package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val buttonBack=findViewById<Button>(R.id.CPAifcvBackPassword)
        val buttonConfirm=findViewById<Button>(R.id.CPAifcvConfirmPassword)

        buttonBack.setOnClickListener {
            val intent = Intent(this, Menu_Activity::class.java)
            startActivity(intent)
        }

        buttonConfirm.setOnClickListener {
            val intent = Intent(this, Menu_Activity::class.java)
            startActivity(intent)
        }

    }
}