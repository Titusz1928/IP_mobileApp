package com.example.ip_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TableRow
import com.google.android.material.floatingactionbutton.FloatingActionButton

class VisualActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual)

        val options = arrayOf("Tensiunea arterială", "Puls", "T. corporală","Greutate","Glicernie","Lumină","T. ambientală","Umiditate","Proximitate")

        val spinner=findViewById<Spinner>(R.id.spinner)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Handle item selection
                val selectedItem = options[position]
                // Do something with the selected item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        val to_profile=findViewById<FloatingActionButton>(R.id.VISbcvBackButton)



        to_profile.setOnClickListener {
            val intent = Intent(this, Menu_Activity::class.java)
            startActivity(intent)
        }



    }
}