package com.everest.moviedb.model

import com.google.gson.annotations.SerializedName

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
    val category: String,

    @SerializedName("overview")
    val desc: String

)