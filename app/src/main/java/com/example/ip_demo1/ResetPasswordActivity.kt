package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val buttonBack=findViewById<Button>(R.id.btBack)
        val buttonNext=findViewById<Button>(R.id.btContinue)


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