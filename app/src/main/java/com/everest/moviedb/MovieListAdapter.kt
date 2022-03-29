package com.everest.moviedb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.everest.moviedb.ui.MovieDetail

class MovieListAdapter(private val movies: List<MovieDetail> = listOf()) :
    RecyclerView.Adapter<MovieListAdapter.MovieListItemViewHolder>() {

    private lateinit var listener: CardClickListener

    interface CardClickListener {
        fun onCardClick(position: Int)
    }

    fun setCardClickListener(cardClickListener: CardClickListener) {
        listener = cardClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListItemViewHolder {
        return MovieListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movielist_item, parent, false),
            this.listener
        )
    }

    override fun onBindViewHolder(holder: MovieListItemViewHolder, position: Int) {
        val movie = movies[position]
        val imageURL = movie.imageUrl
        holder.nameTV.text = movie.name
        holder.releaseDateTV.text = movie.release_date
        holder.ratingTV.text = movie.rating

        Glide.with(holder.itemView.context).load(imageURL).into(holder.movieImage)

    }

    override fun getItemCount(): Int = movies.size

    inner class MovieListItemViewHolder(
        view: View,
        cardClickListener: CardClickListener
    ) : RecyclerView.ViewHolder(view) {

        val nameTV = view.findViewById<TextView>(R.id.movie_name_tv)
        val releaseDateTV = view.findViewById<TextView>(R.id.movieReleaseDateTV)
        val ratingTV = view.findViewById<TextView>(R.id.movieVotingTV)
        val movieImage = view.findViewById<AppCompatImageView>(R.id.movie_image)

        init {
            view.setOnClickListener {
                cardClickListener.onCardClick(adapterPosition)
            }
        }
    }

}

