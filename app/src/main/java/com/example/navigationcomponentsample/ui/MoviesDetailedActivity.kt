package com.example.navigationcomponentsample.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.navigationcomponentsample.R
import com.example.navigationcomponentsample.databinding.ActivityMoviesDetailedBinding

class MoviesDetailedActivity : AppCompatActivity() {
    private var binding: ActivityMoviesDetailedBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivityMoviesDetailedBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.toolbar?.setNavigationIcon(R.drawable.ic_back_arrow_white)
        binding?.toolbar?.title = intent.getStringExtra("name")
        binding?.toolbar?.setNavigationOnClickListener { onBackPressed() }

        binding?.tvMovieName?.text = intent.getStringExtra("name")
        binding?.tvMovieCategory?.text = intent.getStringExtra("category")
        binding?.tvMovieDesc?.text = intent.getStringExtra("desc")

        binding?.collapsingToolbarImage?.let {
            Glide.with(this)
                .load(intent.getStringExtra("imageUrl"))
                .into(it)
        }
    }

}