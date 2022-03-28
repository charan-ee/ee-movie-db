package com.everest.moviedb.callbacks

import com.everest.moviedb.model.Movie
import com.everest.moviedb.ui.MovieDetail

interface ApiResponseCallback {

    fun onSuccess(apiResponse: List<MovieDetail>);

    fun onFailure(message: String)
}