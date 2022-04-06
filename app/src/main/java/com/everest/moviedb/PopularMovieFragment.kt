package com.everest.moviedb

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.everest.moviedb.databinding.PopularMovieLayoutBinding
import com.everest.moviedb.ui.MovieDetail
import com.everest.moviedb.utils.MOVIE_DETAILS

class PopularMovieFragment : Fragment(R.layout.popular_movie_layout) {
    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: PopularMovieLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PopularMovieLayoutBinding.inflate(inflater, container, false)
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
        } ?: throw RuntimeException("No activity found")

        viewModel.movieList.observe(viewLifecycleOwner) {
            setAdapter(it)
        }

        viewModel.getPopularMovies()

    }

    private fun setAdapter(movies: List<MovieDetail>?) {
        binding.popularMovieRV.layoutManager = LinearLayoutManager(activity).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        val movieAdapter = movies?.let { MovieListAdapter(it) }
        binding.popularMovieRV.adapter = movieAdapter

        movieAdapter?.setCardClickListener(object : MovieListAdapter.CardClickListener {
            override fun onCardClick(position: Int) {
                viewModel.movieList.value?.let { getMovieDetails(it.get(position)) }
            }
        })

    }

    fun getMovieDetails(movie: MovieDetail) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_DETAILS, movie)
        startActivity(intent)
    }

}