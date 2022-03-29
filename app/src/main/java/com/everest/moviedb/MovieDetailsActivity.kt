package com.everest.moviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.everest.moviedb.databinding.ActivityMovieDetailsBinding
import com.everest.moviedb.ui.MovieDetail
import com.everest.moviedb.utils.MOVIE_DETAILS

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieDetails = intent.getParcelableExtra<MovieDetail>(MOVIE_DETAILS)

        binding.movieNameTv.text = movieDetails!!.name
        binding.movieDetailDescTV.text = movieDetails.desc
        Glide.with(this).load(movieDetails.imageUrl).into(binding.movieImageIV)
    }
}