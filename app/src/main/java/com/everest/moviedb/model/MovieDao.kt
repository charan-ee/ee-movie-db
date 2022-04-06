package com.everest.moviedb.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("select * from movie_details")
    fun getPopularMovies(): List<Movie>

    @Query("select * from movie_details where release_date < :current_date ORDER BY release_date DESC")
    fun getCurrentMovies(current_date: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movies: List<Movie>)
}