package com.everest.moviedb

import com.everest.moviedb.model.MovieService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val BASE_URL = "https://api.themoviedb.org/3/"

    val api_interceptor = Interceptor {
        val originalRequest = it.request()
        val newHttpUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()
        it.proceed(newRequest)
    }

    val clientHttp = OkHttpClient().newBuilder()
        .addNetworkInterceptor(api_interceptor)
        .build()

    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val movieService: MovieService by lazy {
        getClient().create(MovieService::class.java)
    }
}
