package com.everest.moviedb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.everest.moviedb.databinding.PopularMovieLayoutBinding
import com.everest.moviedb.model.Movie
import com.everest.moviedb.model.MovieDatabase
import com.everest.moviedb.model.MovieRepository
import java.lang.RuntimeException

class PopularMovieFragment : Fragment(R.layout.popular_movie_layout) {

    lateinit var binding: PopularMovieLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PopularMovieLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val movieDatabase = MovieDatabase.getInstance(requireContext())

        val movieRepository = (requireContext().applicationContext as MovieApplication).movieRepository

        val viewModel = activity?.let {
            ViewModelProvider (it, MovieViewModelFactory(movieRepository))[MovieViewModel::class.java]
        }?:throw RuntimeException("No activity found")
        val popularMovieRV= binding.popularMovieRV

        popularMovieRV.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        popularMovieRV.adapter=MovieListAdapter(listOf(Movie(0,"","","","", "", "", "")))

        viewModel.getAllMovies()
        viewModel.movieList.observe(viewLifecycleOwner){
            popularMovieRV.adapter=MovieListAdapter(it)

        }

    }

}