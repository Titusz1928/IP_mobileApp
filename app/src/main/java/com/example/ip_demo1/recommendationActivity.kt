package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class recommendationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation)

        val button=findViewById<FloatingActionButton>(R.id.btBack_to_home)

        button.setOnClickListener {
            val intent = Intent(this, FisaMedicalaActivity::class.java)
            startActivity(intent)
        }
    }
}