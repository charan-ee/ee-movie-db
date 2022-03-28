package com.everest.moviedb.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular?language=en-US&page=1")
    fun getAllMovies(): Call<APIResponse>

    @GET("movie/now_playing?language=en-US&page=1")
    fun getCurrentPlayingMovies(): Call<APIResponse>

    @GET("search/movie?language=en-US&page=1&include_adult=false")
    fun getMoviesByName(@Query("query") query: String): Call<APIResponse>

}