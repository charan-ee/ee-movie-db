package com.everest.moviedb.callbacks

import com.everest.moviedb.model.Movie

interface ApiResponseCallback {

    fun onSuccess(apiResponse: List<Movie>);

    fun onFailure(message: String)
}