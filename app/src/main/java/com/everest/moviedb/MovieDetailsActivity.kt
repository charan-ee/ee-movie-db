package com.everest.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.everest.moviedb.databinding.ActivityMovieDetailsBinding
import com.everest.moviedb.utils.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val  binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.extras?.getString(NAME)
        val description = intent.extras?.getString(OVERVIEW)
        val imageUrl = intent.extras?.getString(IMAGE_URL)

        binding.movieNameTv.text = name
        binding.movieDetailDescTV.text = description
        Glide.with(this).load(imageUrl).into(binding.movieImageIV)

    }
}