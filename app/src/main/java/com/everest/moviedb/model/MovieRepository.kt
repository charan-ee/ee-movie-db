package com.everest.moviedb.model

import android.util.Log
import com.everest.moviedb.callbacks.ApiResponseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository constructor(
    private val movieApiClient: MovieService,
    private val movieDatabaseClient: MovieDatabase
) {

    fun getAllMovies(apiResponseCallback: ApiResponseCallback){
        var movieList: List<Movie>
        val response = movieApiClient.getAllMovies()

        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.body() != null) {
                    response.body()?.movies?.let { apiResponseCallback.onSuccess(it) }
                    movieList = response.body()?.movies!!
                    movieDatabaseClient.movieDao().insertAllMovies(movieList)
                } else {
                    apiResponseCallback.onFailure("Error in ApiResponseCallback")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                movieList = movieDatabaseClient.movieDao().getPopularMovies()
                apiResponseCallback.onSuccess(movieList)
            }
        })
    }

    fun getCurrentPlayingMovies(apiResponseCallback: ApiResponseCallback) {
        var movieList: List<Movie>
        val response = movieApiClient.getCurrentPlayingMovies()

        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.body() != null) {
                    response.body()?.movies?.let { apiResponseCallback.onSuccess(it) }
                    movieList = response.body()?.movies!!
                    movieDatabaseClient.movieDao().insertAllMovies(movieList)
                }else {
                    apiResponseCallback.onFailure("Error in the callback")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                movieList = movieDatabaseClient.movieDao().getCurrentMovies()
                apiResponseCallback.onSuccess(movieList)
            }
        })
    }

    fun getMoviesByName(query: String, apiResponseCallback: ApiResponseCallback) {
        val response = movieApiClient.getMoviesByName(query)

        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.body() != null) {
                    response.body()?.movies?.let { apiResponseCallback.onSuccess(it) }
                }else {
                    apiResponseCallback.onFailure("Error in the callback")
                }
            }
            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
            }
        })
    }
}