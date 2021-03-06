package com.example.navigationcomponentsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.databinding.ActivityViewPagerBinding
import com.example.navigationcomponentsample.viewpagerfragments.PageAdapter
import com.google.android.material.tabs.TabLayout

class ViewPagerActivity : AppCompatActivity() {

    private var binding: ActivityViewPagerBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        val viewPager = binding?.viewPager
        viewPager?.adapter = PageAdapter(supportFragmentManager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}