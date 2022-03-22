package com.everest.moviedb

import android.app.Application
import com.everest.moviedb.model.MovieDatabase
import com.everest.moviedb.model.MovieRepository

class MovieApplication: Application() {


    val database by lazy { MovieDatabase.getInstance(this) }

}