package com.everest.moviedb

import com.everest.moviedb.model.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var movieService: MovieService? = null

    const val BASE_URL = R.string.API_BaseUrl.toString()

    fun getClient(): MovieService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            movieService = retrofit.create(MovieService::class.java)
        return movieService!!
    }
}