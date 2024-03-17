package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FisaMedicalaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fisa_medicala)

        val button=findViewById<FloatingActionButton>(R.id.btBack_to_home)

        button.setOnClickListener {
            val intent = Intent(this, Menu_Activity::class.java)
            startActivity(intent)
        }



    }
}