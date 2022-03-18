package com.everest.moviedb.model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieService {

    @GET("movies")
    fun getAllMovies(): Call<List<Movie>>


    companion object {
        var movieService: MovieService? = null

        const val BASE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=4fcef1da977950737a961196b2c901e0"

        fun getClient(): MovieService {
            if (movieService == null ) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                movieService = retrofit.create(MovieService::class.java)
            }
            return movieService!!
        }
    }
}