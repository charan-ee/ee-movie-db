package com.everest.moviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.everest.moviedb.databinding.CurrentMovieLayoutBinding
import com.everest.moviedb.model.Movie
import com.everest.moviedb.model.MovieRepository
import java.lang.RuntimeException

class CurrentMovieFragment : Fragment(R.layout.current_movie_layout) {

    lateinit var binding: CurrentMovieLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CurrentMovieLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = activity?.let { ViewModelProvider(it)[MovieViewModel::class.java] }
            ?: throw RuntimeException("No Activity Found")
        val movieRepository = MovieRepository(RetrofitClient.getClient())
        val currentMovieRV = binding.currentMovieRV
        currentMovieRV.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        currentMovieRV.adapter = MovieListAdapter(listOf(Movie(0, "", "", "", "", "", "", "")))

        viewModel.getCurrentPlayingMovies(movieRepository)
        viewModel.currentMovieList.observe(viewLifecycleOwner) {
            currentMovieRV.adapter = MovieListAdapter(it)
        }

    }
}