package com.everest.moviedb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everest.moviedb.callbacks.ApiResponseCallback
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
        movieRepository.getAllMovies(object : ApiResponseCallback {
            override fun onSuccess(apiResponse: List<Movie>) {
                Log.i("response", apiResponse.toString())
                _movieList.value = apiResponse
            }
            override fun onFailure(message: String) {
            }
        })
    }


    fun getCurrentPlayingMovies() {
        movieRepository.getCurrentPlayingMovies(object : ApiResponseCallback {
            override fun onSuccess(apiResponse: List<Movie>) {
                _currentMovieList.value = apiResponse
            }
            override fun onFailure(message: String) {
            }
        })
    }

    fun getMoviesByName(query: String) {
        movieRepository.getMoviesByName(query, object : ApiResponseCallback {
            override fun onSuccess(apiResponse: List<Movie>) {
                _searchMovieList.value = apiResponse
            }
            override fun onFailure(message: String) {
            }
        })
    }


}