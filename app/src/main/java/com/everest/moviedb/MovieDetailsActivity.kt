package com.everest.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.everest.moviedb.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val  binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.extras?.getString("name")
        val description = intent.extras?.getString("overview")
        val imageUrl = intent.extras?.getString("imageUrl")

        binding.movieNameTv.text = name
        binding.movieDetailDescTV.text = description
        Log.i("image", imageUrl.toString())
        Glide.with(this).load(imageUrl).into(binding.movieImageIV)

    }
}