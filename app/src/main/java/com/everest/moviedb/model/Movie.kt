package com.everest.moviedb.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie(
    @SerializedName("id")
    val id: Int,

    @SerializedName("original_title")
    val name: String,

    @SerializedName("poster_path")
    val imageUrl: String,

    @SerializedName("background_path")
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