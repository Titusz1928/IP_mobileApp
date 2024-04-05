package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class s_chat1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schat1)

        val back=findViewById<FloatingActionButton>(R.id.SCHbcvBackButton)

        val nestedScrollView = findViewById<NestedScrollView>(R.id.clNestedScrollView)

        nestedScrollView.post {
            nestedScrollView.fullScroll(View.FOCUS_DOWN)
        }

        back.setOnClickListener{
            val intent = Intent(this, Menu_Activity::class.java)
            startActivity(intent)
        }

    }
}