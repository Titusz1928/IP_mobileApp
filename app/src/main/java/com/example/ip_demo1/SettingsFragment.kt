package com.example.ip_demo1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView


class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val buttonLogout = view.findViewById<Button>(R.id.SETlogoutButton)
        val buttonChangePassword = view.findViewById<Button>(R.id.SETresetpasswordButton)
        val buttonChangeName=view.findViewById<ImageView>(R.id.imageView_nume)

        buttonChangeName.setOnClickListener{
            val intent = Intent(requireContext(), ChangeInformation::class.java)
            startActivity(intent)
        }


        buttonLogout.setOnClickListener {
            // Navigate to MainActivity
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }

        buttonChangePassword.setOnClickListener {
            // Navigate to MainActivity
            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        return view
    }


}