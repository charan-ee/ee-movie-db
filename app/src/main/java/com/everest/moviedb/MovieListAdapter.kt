package com.everest.moviedb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everest.moviedb.model.Movie
import com.everest.moviedb.ui.MovieDetail
import com.everest.moviedb.utils.IMAGE_URL
import com.everest.moviedb.utils.NAME
import com.everest.moviedb.utils.OVERVIEW

class MovieListAdapter(private val movies: List<MovieDetail> = ArrayList<MovieDetail>()) : RecyclerView.Adapter<MovieListItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListItemViewHolder {
        return MovieListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movielist_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieListItemViewHolder, position: Int) {
        val movie = movies[position]
        val imageURL = movie.imageUrl
        holder.nameTV.text = movie.name
        holder.releaseDateTV.text = movie.release_date
        holder.ratingTV.text = movie.rating

        Glide.with(holder.itemView.context).load(imageURL).into(holder.movieImage)

        holder.movieItemCL.setOnClickListener { view ->
            val intent = Intent(view.context, MovieDetailsActivity::class.java)
            intent.putExtra(OVERVIEW, movie.desc)
            intent.putExtra(NAME, movie.name)
            intent.putExtra(IMAGE_URL, imageURL)
            view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = movies.size


}

