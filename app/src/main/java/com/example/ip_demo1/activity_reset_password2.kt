package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class activity_reset_password2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password2)

        val buttonBack=findViewById<Button>(R.id.RPA2ifcvBackButton)
        val buttonNext=findViewById<Button>(R.id.RPA2ifcvConfirmButton)


        buttonBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonNext.setOnClickListener{
            val intent = Intent(this, activity_reset_password3::class.java)
            startActivity(intent)
        }

    }
}