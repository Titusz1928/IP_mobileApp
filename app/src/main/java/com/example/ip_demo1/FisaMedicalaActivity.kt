package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TableRow
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FisaMedicalaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fisa_medicala)

        val button=findViewById<FloatingActionButton>(R.id.btBack_to_home)

        val to_recomButton = findViewById<TableRow>(R.id.row1)


        button.setOnClickListener {
            val intent = Intent(this, Menu_Activity::class.java)
            startActivity(intent)
        }

        to_recomButton.setOnClickListener {
            val intent = Intent(this, recommendationActivity::class.java)
            startActivity(intent)
        }

    }
}