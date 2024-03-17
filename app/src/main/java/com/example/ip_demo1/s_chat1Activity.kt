package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class s_chat1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schat1)

        val back=findViewById<FloatingActionButton>(R.id.btBack_to_chat)

        back.setOnClickListener{
            val intent = Intent(this, Menu_Activity::class.java)
            startActivity(intent)
        }

    }
}