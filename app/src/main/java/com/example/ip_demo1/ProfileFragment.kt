package com.example.ip_demo1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val to_actualizare = view.findViewById<CardView>(R.id.llActualizareCardView)
        val to_vizualizare = view.findViewById<CardView>(R.id.llVizualizareCardView)

        to_actualizare.setOnClickListener {

            val intent = Intent(requireContext(), ActualizareActivity::class.java)
            startActivity(intent)
        }

        to_vizualizare.setOnClickListener {

            val intent = Intent(requireContext(), VisualActivity::class.java)
            startActivity(intent)
        }


        return view
    }


}