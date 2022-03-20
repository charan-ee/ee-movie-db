package com.everest.moviedb.model

class MovieRepository constructor(private val movieService: MovieService) {
    fun getAllMovies() = movieService.getAllMovies()

    fun getCurrentPlayingMovies() = movieService.getCurrentPlayingMovies()
}