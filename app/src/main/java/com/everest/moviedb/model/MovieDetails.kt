package com.everest.moviedb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetails (
    val id: Int,

    @PrimaryKey
    val name: String,

    val imageUrl: String,

    val backgroundUrl: String,

    val language: String,

    val desc: String,

    val release_date: String,

    val rating: String
)