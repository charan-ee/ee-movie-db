package com.everest.moviedb.model

import com.google.gson.annotations.SerializedName

data class APIResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("per_page")
    val perPage: Int,

    @SerializedName("total")
    val total: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("results")
    val movies: List<Movie>

)

data class Movie(

    @SerializedName("id")
    val id: Int,

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

