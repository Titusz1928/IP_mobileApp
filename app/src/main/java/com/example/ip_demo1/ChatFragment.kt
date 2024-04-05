package com.example.ip_demo1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView

class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        val to_s_chat = view.findViewById<CardView>(R.id.llChat2CardView)

        to_s_chat.setOnClickListener {
            val intent = Intent(requireContext(), s_chat1Activity::class.java)
            requireContext().startActivity(intent)
        }

        return view
    }
}
