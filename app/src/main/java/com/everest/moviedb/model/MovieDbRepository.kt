package com.everest.moviedb.model

import androidx.lifecycle.LiveData

class MovieDbRepository(private val movieDao: MovieDao) {

    suspend fun insertMovies(movies: List<Movie>){
        movieDao.insertAllMovies(movies)
    }

    fun getAllMovies(): LiveData<List<Movie>> = movieDao.getPopularMovies()
}