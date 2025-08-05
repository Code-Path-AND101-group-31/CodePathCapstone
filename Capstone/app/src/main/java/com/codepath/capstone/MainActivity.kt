package com.codepath.capstone

import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //***** main activity container ******
    private lateinit var container: FrameLayout
    //***** main activity container ******

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mainLayout = findViewById<View>(R.id.main)

        //******************************************************
        //**** nav bar and main activity container *************
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavBar) //changed this line to use BottomNavigationView. gives setOnItemSelectedListener method to bottomNavBar -Alex
        container = findViewById(R.id.fragment_container)
        //**** nav bar and main activity container *************
        //******************************************************

        //***********************************
        //***** code for the nav bar ********
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(bottomNavBar) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(bottom = systemBars.bottom)
            insets
        }
        //***** code for the nav bar *******
        //**********************************

    //***********************************************************************
    //*** code for giving functionality to the nav bar WORK IN PROGRESS -Alex
        loadLayout(R.layout.activity_main)

        bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.nav_home -> {
                    loadLayout(R.layout.activity_main)
                    true
                }
                R.id.nav_search -> {
                    loadLayout(R.layout.search_component)
                    true
                }
                else -> false
            }
        }
    }

    private fun loadLayout(layoutResId: Int){
        container.removeAllViews()
        val view = LayoutInflater.from(this).inflate(layoutResId, container, false)
        container.addView(view)
    }

    //*** End of nav bar functionality code block ******
    //**************************************************
}
