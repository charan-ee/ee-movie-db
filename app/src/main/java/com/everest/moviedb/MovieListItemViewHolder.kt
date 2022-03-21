package com.everest.moviedb

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MovieListItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTV = view.findViewById<TextView>(R.id.movie_name_tv)
    val releaseDateTV = view.findViewById<TextView>(R.id.movieReleaseDateTV)
    val ratingTV = view.findViewById<TextView>(R.id.movieVotingTV)
    val movieImage = view.findViewById<AppCompatImageView>(R.id.movie_image)
    val movieItemCL = view.findViewById<ConstraintLayout>(R.id.movieItemCL)
}