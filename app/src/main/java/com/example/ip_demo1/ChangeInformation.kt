package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChangeInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_information)

        val buttonSaveData=findViewById<Button>(R.id.CINbcvSaveButton)

        buttonSaveData.setOnClickListener{
            val intent = Intent(this, Menu_Activity::class.java)
            startActivity(intent)
        }

    }
}