package com.everest.moviedb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everest.moviedb.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var _movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()
    val movieList: LiveData<List<Movie>> = _movieList

    private val _currentMovieList = MutableLiveData<List<Movie>>()
    val currentMovieList: LiveData<List<Movie>> = _currentMovieList

    private val _searchMovieList = MutableLiveData<List<Movie>>()
    val searchMovieList: LiveData<List<Movie>> = _searchMovieList


    fun getAllMovies() {
        val response = movieRepository.getAllMovies()
        _movieList.value = response
    }


    fun getCurrentPlayingMovies() {
        val response = movieRepository.getCurrentPlayingMovies()
        _currentMovieList.value = response
    }

    fun getMoviesByName(query: String) {
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