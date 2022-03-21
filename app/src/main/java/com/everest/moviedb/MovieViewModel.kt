package com.everest.moviedb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.everest.moviedb.model.APIResponse
import com.everest.moviedb.model.Movie
import com.everest.moviedb.model.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val _movieList = MutableLiveData(listOf(Movie(0, "", "", "", "", "")))
    val errorMessage = MutableLiveData<String>()
    val movieList: LiveData<List<Movie>> = _movieList

    private val _currentMovieList = MutableLiveData(listOf(Movie(0, "", "", "", "", "")))
    val currentMovieList: LiveData<List<Movie>> = _currentMovieList

    private val _searchMovieList = MutableLiveData(listOf(Movie(0, "", "", "", "", "")))
    val searchMovieList: LiveData<List<Movie>> = _searchMovieList



    fun getAllMovies(movieRepository: MovieRepository) {
        val response = movieRepository.getAllMovies()
        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                _movieList.postValue(response.body()?.movies)
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