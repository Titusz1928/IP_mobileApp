package com.example.ip_demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Menu_Activity : AppCompatActivity() {

    private lateinit var bottomNavigationView:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        bottomNavigationView = findViewById(R.id.clBottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home->{
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.profile->{
                    replaceFragment(ProfileFragment())
                    true
                }
                R.id.chat->{
                    replaceFragment(ChatFragment())
                    true
                }
                R.id.settings->{
                    replaceFragment(SettingsFragment())
                    true
                }
                else-> false
            }
        }

        replaceFragment(HomeFragment())

    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.clFrameLayout, fragment).commit()
    }
}