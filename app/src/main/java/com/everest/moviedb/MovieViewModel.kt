package com.everest.moviedb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everest.moviedb.callbacks.ApiResponseCallback
import com.everest.moviedb.model.*
import com.everest.moviedb.ui.MovieDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var _movieList = MutableLiveData<List<MovieDetail>>()
    val errorMessage = MutableLiveData<String>()
    val movieList: LiveData<List<MovieDetail>> = _movieList

    private val _currentMovieList = MutableLiveData<List<MovieDetail>>()
    val currentMovieList: LiveData<List<MovieDetail>> = _currentMovieList

    private val _searchMovieList = MutableLiveData<List<MovieDetail>>()
    val searchMovieList: LiveData<List<MovieDetail>> = _searchMovieList


    fun getAllMovies() {
        movieRepository.getAllMovies(object : ApiResponseCallback {
            override fun onSuccess(apiResponse: List<MovieDetail>) {
                Log.i("response", apiResponse.toString())
                _movieList.value = apiResponse
            }
            override fun onFailure(message: String) {
            }
        })
    }


    fun getCurrentPlayingMovies() {
        movieRepository.getCurrentPlayingMovies(object : ApiResponseCallback {
            override fun onSuccess(apiResponse: List<MovieDetail>) {
                _currentMovieList.value = apiResponse
            }
            override fun onFailure(message: String) {
            }
        })
    }

    fun getMoviesByName(query: String) {
        movieRepository.getMoviesByName(query, object : ApiResponseCallback {
            override fun onSuccess(apiResponse: List<MovieDetail>) {
                _searchMovieList.value = apiResponse
            }
            override fun onFailure(message: String) {
            }
        })
    }


}