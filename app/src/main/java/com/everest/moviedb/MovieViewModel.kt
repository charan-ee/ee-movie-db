package com.everest.moviedb

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everest.moviedb.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(context: Context) : AndroidViewModel(context as Application){

    @SuppressLint("StaticFieldLeak")
    private val applicationContext = getApplication<Application>().applicationContext

    private val movieDatabase = MovieDatabase.getInstance(applicationContext)

    private val _movieList = MutableLiveData(listOf(Movie(0, "", "", "", "", "", "", "")))
    val errorMessage = MutableLiveData<String>()
    val movieList: LiveData<List<Movie>> = _movieList

    private val _currentMovieList = MutableLiveData(listOf(Movie(0, "", "", "", "", "", "", "")))
    val currentMovieList: LiveData<List<Movie>> = _currentMovieList

    private val _searchMovieList = MutableLiveData(listOf(Movie(0, "", "", "", "", "", "", "")))
    val searchMovieList: LiveData<List<Movie>> = _searchMovieList


    fun getAllMovies(movieRepository: MovieRepository) {
        val response = movieRepository.getAllMovies()

        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if(response.body() != null){
                    response.body()?.movies?.let { movieDatabase.movieDao().insertAllMovies(it) }
                    _movieList.postValue(response.body()?.movies)
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getCurrentPlayingMovies(movieRepository: MovieRepository) {
        val response = movieRepository.getCurrentPlayingMovies()
        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                _currentMovieList.postValue(response.body()?.movies)
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getMoviesByName(movieRepository: MovieRepository, query: String) {
        val response = movieRepository.getMoviesByName(query)
        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                _searchMovieList.postValue(response.body()?.movies)
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }


}