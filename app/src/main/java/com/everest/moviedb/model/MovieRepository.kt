package com.everest.moviedb.model

import com.everest.moviedb.callbacks.ApiResponseCallback
import com.everest.moviedb.ui.MovieDetail
import com.everest.moviedb.utils.CurrentDate
import com.everest.moviedb.utils.IMAGE_BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository constructor(
    private val movieApiClient: MovieService,
    private val movieDatabaseClient: MovieDatabase
) {
    fun getAllMovies(apiResponseCallback: ApiResponseCallback) {
        var movieList: List<Movie>
        val response = movieApiClient.getAllMovies()

        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.body() != null) {
                    apiResponseCallback.onResponse(convertDTOIntoUIModel(response.body()!!.movies))
                    movieList = response.body()?.movies!!
                    movieDatabaseClient.movieDao().insertAllMovies(movieList)
                } else {
                    apiResponseCallback.onFailure("Error in ApiResponseCallback")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                movieList = movieDatabaseClient.movieDao().getPopularMovies()
                apiResponseCallback.onResponse(convertDTOIntoUIModel(movieList))
            }
        })
    }

    fun getCurrentPlayingMovies(apiResponseCallback: ApiResponseCallback) {
        var movieList: List<Movie>
        val response = movieApiClient.getCurrentPlayingMovies()
        val currentDate = CurrentDate().getCurrentDate()

        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.body() != null) {
                    response.body()?.movies?.let {
                        apiResponseCallback.onResponse(
                            convertDTOIntoUIModel(it)
                        )
                    }
                    movieList = response.body()?.movies!!
                    movieDatabaseClient.movieDao().insertAllMovies(movieList)
                } else {
                    apiResponseCallback.onFailure("Error in the callback")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                movieList = movieDatabaseClient.movieDao().getCurrentMovies(currentDate)
                apiResponseCallback.onResponse(convertDTOIntoUIModel(movieList))
            }
        })
    }

    fun getMoviesByName(query: String, apiResponseCallback: ApiResponseCallback) {
        val response = movieApiClient.getMoviesByName(query)

        response.enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                if (response.body() != null) {
                    response.body()?.movies?.let {
                        apiResponseCallback.onResponse(
                            convertDTOIntoUIModel(it)
                        )
                    }
                } else {
                    apiResponseCallback.onFailure("Error in the callback")
                }
            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
            }
        })
    }

    private fun convertDTOIntoUIModel(movies: List<Movie>): List<MovieDetail> {
        return movies.map {
            MovieDetail(
                it.name,
                IMAGE_BASE_URL + it.imageUrl,
                it.language,
                it.desc,
                it.release_date,
                it.rating
            )
        }
    }
}