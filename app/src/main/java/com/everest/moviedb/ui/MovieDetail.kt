package com.everest.moviedb.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieDetail(
    val name: String,

    val imageUrl: String,

    val language: String,

    val desc: String,

    val release_date: String,

    val rating: String

) : Parcelable