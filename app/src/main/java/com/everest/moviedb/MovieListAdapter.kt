package com.everest.moviedb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everest.moviedb.model.Movie

class MovieListAdapter(val movies: List<Movie>) : RecyclerView.Adapter<MovieListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListItemViewHolder {
        return MovieListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movielist_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieListItemViewHolder, position: Int) {

        val movie = movies[position]
        val imageURL = "https://image.tmdb.org/t/p/w200" + movie.imageUrl
        holder.nameTV.text = movie.name
        holder.duration.text = movie.category
        Glide.with(holder.itemView.context).load(imageURL).into(holder.movieImage)
    }

    override fun getItemCount(): Int = movies.size


}

