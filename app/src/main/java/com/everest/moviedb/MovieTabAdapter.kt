package com.everest.moviedb

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MovieTabAdapter(fragmentManager: FragmentManager):  FragmentStatePagerAdapter(fragmentManager) {
    val categories = listOf(
        R.string.tab_popular.toString(),
        R.string.tab_current.toString()
    )

    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int) =
        when(position){
            0 -> PopularMovieFragment()
            1 -> CurrentMovieFragment()
            else -> error(R.string.error_no_position.toString())
        }

    override fun getPageTitle(position: Int) = categories[position]

}