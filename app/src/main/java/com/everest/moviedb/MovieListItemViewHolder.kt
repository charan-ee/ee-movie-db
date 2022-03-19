package com.everest.moviedb

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

class MovieListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTV = view.findViewById<TextView>(R.id.movie_name_tv)
    val duration = view.findViewById<TextView>(R.id.movie_duration_tv)
    val movieImage = view.findViewById<AppCompatImageView>(R.id.movie_image)
}