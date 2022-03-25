package com.everest.moviedb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_details")
data class Movie2(

    @SerializedName("id")
    val id: Int,

    @PrimaryKey
    @SerializedName("original_title")
    val name: String,

    @SerializedName("poster_path")
    val imageUrl: String,

    @SerializedName("backdrop_path")
    val backgroundUrl: String,

    @SerializedName("original_language")
    val language: String,

    @SerializedName("overview")
    val desc: String,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("vote_average")
    val rating: String


)