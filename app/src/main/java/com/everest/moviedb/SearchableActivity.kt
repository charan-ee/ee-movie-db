package com.everest.moviedb

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.everest.moviedb.databinding.ActivitySearchableBinding
import com.everest.moviedb.model.MovieRepository
import com.everest.moviedb.ui.MovieDetail
import com.everest.moviedb.utils.MOVIE_DETAILS

class SearchableActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchableBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieRepository = (application as MovieApplication).movieRepository
        viewModel = ViewModelProvider(
            this@SearchableActivity,
            MovieViewModelFactory(movieRepository)
        )[MovieViewModel::class.java]

        searchMovie()
    }

    private fun searchMovie() {
        viewModel.searchMovieList.observe(this@SearchableActivity) {
            setAdapter(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_search, menu)
        val searchView: SearchView = updateSearchView(menu)
        setSearchManager(menu)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String): Boolean {
                viewModel.getMoviesByName(query)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
        return true
    }

    private fun setSearchManager(menu: Menu) {
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
    }

    private fun updateSearchView(menu: Menu): SearchView {
        val searchView: SearchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.isIconified = false
        return searchView
    }

    private fun setAdapter(movies: List<MovieDetail>?) {
        binding.searchMovieRV.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        val movieAdapter = movies?.let { MovieListAdapter(it) }
        binding.searchMovieRV.adapter = movieAdapter

        movieAdapter?.setCardClickListener(object : MovieListAdapter.CardClickListener {
            override fun onCardClick(position: Int) {
                viewModel.searchMovieList.value?.let { getMovieDetails(it[position]) }
            }
        })
    }

    fun getMovieDetails(movie: MovieDetail) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_DETAILS, movie)
        startActivity(intent)
    }


}