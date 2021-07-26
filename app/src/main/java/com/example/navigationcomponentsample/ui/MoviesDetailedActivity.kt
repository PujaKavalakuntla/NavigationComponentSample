package com.example.navigationcomponentsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.navigationcomponentsample.databinding.ItemMovieBinding

class MoviesDetailedActivity : AppCompatActivity() {
    private  var binding: ItemMovieBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ItemMovieBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tvMovieName?.text= intent.getStringExtra("name")
        binding?.tvMovieCategory?.text = intent.getStringExtra("category")
        binding?.tvMovieDesc?.text=intent.getStringExtra("desc")

        binding?.ivMovieImg?.let {
            Glide.with(this)
                .load(intent.getStringExtra("imageUrl"))
                .into(it)
        }
    }
}