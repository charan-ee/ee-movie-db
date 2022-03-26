package com.everest.moviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.everest.moviedb.databinding.CurrentMovieLayoutBinding
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

        val movieRepository = (requireContext().applicationContext as MovieApplication).movieRepository
        val viewModel = activity?.let { ViewModelProvider(it, MovieViewModelFactory(movieRepository))[MovieViewModel::class.java] }
            ?: throw RuntimeException("No Activity Found")
        val currentMovieRV = binding.currentMovieRV

        currentMovieRV.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        currentMovieRV.adapter = MovieListAdapter()

        viewModel.getCurrentPlayingMovies()
        viewModel.currentMovieList.observe(viewLifecycleOwner) {
            currentMovieRV.adapter = MovieListAdapter(it)
        }

    }
}