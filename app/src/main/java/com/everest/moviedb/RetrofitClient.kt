package com.everest.moviedb

import com.everest.moviedb.model.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var movieService: MovieService? = null

    const val BASE_URL = "https://api.themoviedb.org/3/"

    fun isInstanceCreated(): Boolean {
        return movieService != null
    }

    fun getClient(): MovieService {
        if (movieService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            movieService = retrofit.create(MovieService::class.java)
        }
        return movieService!!
    }
}