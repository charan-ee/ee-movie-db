package com.everest.moviedb.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import retrofit2.Call

@Dao
interface MovieDao {

    @Query("select * from movie_details")
    fun getPopularMovies(): LiveData<List<Movie>>

    fun getCurrentMovies(): List<Movie>

    @Insert
    fun insertAllMovies(vararg movies: List<Movie>)
}