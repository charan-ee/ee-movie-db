package com.everest.moviedb

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.everest.moviedb.databinding.CurrentMovieLayoutBinding
import com.everest.moviedb.ui.MovieDetail
import com.everest.moviedb.utils.MOVIE_DETAILS

class CurrentMovieFragment : Fragment(R.layout.current_movie_layout) {

    private lateinit var binding: CurrentMovieLayoutBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CurrentMovieLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieRepository =
            (requireContext().applicationContext as MovieApplication).movieRepository

        viewModel = activity?.let {
            ViewModelProvider(
                it,
                MovieViewModelFactory(movieRepository)
            )[MovieViewModel::class.java]
        }
            ?: throw RuntimeException("No Activity Found")

        viewModel.currentMovieList.observe(viewLifecycleOwner) {
            setAdapter(it)
        }

        viewModel.getCurrentPlayingMovies()

    }

    private fun setAdapter(movies: List<MovieDetail>?) {
        binding.currentMovieRV.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        val movieAdapter = movies?.let { MovieListAdapter(it) }
        binding.currentMovieRV.adapter = movieAdapter

        movieAdapter?.setCardClickListener(object : MovieListAdapter.CardClickListener {
            override fun onCardClick(position: Int) {
                viewModel.currentMovieList.value?.let { getMovieDetails(it.get(position)) }
            }
        })

    }

    fun getMovieDetails(movie: MovieDetail) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_DETAILS, movie)
        startActivity(intent)
    }
}