package com.everest.moviedb.model

data class Movie(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val category: String,
    val desc: String
)