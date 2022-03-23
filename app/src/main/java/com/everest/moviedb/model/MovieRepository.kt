package com.everest.moviedb.model

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository constructor(
    private val movieApiClient: MovieService,
    private val movieDatabaseClient: MovieDatabase
) {

    fun getAllMovies(): List<Movie> {
        var movieList: List<Movie>
        val response = movieApiClient.getAllMovies()

        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.body() != null) {
                    movieList = response.body()?.movies!!
                    movieDatabaseClient.movieDao().insertAllMovies(movieList)
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                t.message
            }
        })
        return movieDatabaseClient.movieDao().getPopularMovies()
    }

    fun getCurrentPlayingMovies(): List<Movie> {
        var movieList: List<Movie>
        val response = movieApiClient.getCurrentPlayingMovies()

        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.body() != null) {
                    movieList = response.body()?.movies!!
                    movieDatabaseClient.movieDao().insertAllMovies(movieList)
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                t.message
            }
        })
        return movieDatabaseClient.movieDao().getCurrentMovies()
    }

    fun getMoviesByName(query: String) = movieApiClient.getMoviesByName(query)
}