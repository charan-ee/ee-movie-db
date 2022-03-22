package com.everest.moviedb

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.everest.moviedb.databinding.ActivitySearchableBinding
import com.everest.moviedb.model.Movie
import com.everest.moviedb.model.MovieRepository

class SearchableActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchableBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchableBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_search, menu)
        val searchView: SearchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.isIconified = false



        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                val viewModel = ViewModelProvider(this@SearchableActivity)[MovieViewModel::class.java]
                val movieRepository = MovieRepository(RetrofitClient.getClient())
                val searchMovieRV = binding.searchMovieRV
                searchMovieRV.layoutManager = LinearLayoutManager(this@SearchableActivity).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }
                searchMovieRV.adapter = MovieListAdapter(listOf(Movie(0, "", "", "", "", "", "", "")))

                viewModel.getMoviesByName(movieRepository, newText)
                viewModel.searchMovieList.observe(this@SearchableActivity) {
                    searchMovieRV.adapter = MovieListAdapter(it)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })

//        val closeButton = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
//
//        closeButton.setOnClickListener {
//            val editText = findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
//            editText.setText("")
//
//            searchView.setQuery("", false)
//            searchView.onActionViewCollapsed()
//        }


        return true
    }


}