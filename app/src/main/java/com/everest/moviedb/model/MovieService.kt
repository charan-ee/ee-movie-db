package com.everest.moviedb.model

import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("popular?api_key=4fcef1da977950737a961196b2c901e0&language=en-US&page=1")
    fun getAllMovies(): Call<APIResponse>

    @GET("now_playing?api_key=4fcef1da977950737a961196b2c901e0&language=en-US&page=1")
    fun getCurrentPlayingMovies(): Call<APIResponse>

}