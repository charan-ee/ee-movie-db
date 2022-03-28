package com.everest.moviedb

import com.everest.moviedb.model.MovieService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var movieService: MovieService? = null

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "4fcef1da977950737a961196b2c901e0"

    fun isInstanceCreated(): Boolean {
        return movieService != null
    }

    val api_interceptor = Interceptor {
        val originalRequest = it.request()
        val newHttpUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()
        it.proceed(newRequest)
    }

    val clientHttp = OkHttpClient().newBuilder()
        .addNetworkInterceptor(api_interceptor)
        .build()

    fun getClient(): MovieService {
        if (movieService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            movieService = retrofit.create(MovieService::class.java)
        }
        return movieService!!
    }
}