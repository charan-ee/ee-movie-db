package com.everest.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var mainScreenPager: ViewPager
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainScreenPager = findViewById(R.id.home_screen_pager)
        val movieTabAdapter = MovieTabAdapter(supportFragmentManager)
        mainScreenPager.adapter = movieTabAdapter

        val tabLayout = findViewById<TabLayout>(R.id.home_screen_tab_layout)
        tabLayout.setupWithViewPager(mainScreenPager)


    }
}