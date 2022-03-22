package com.everest.moviedb

import android.content.res.Resources
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.everest.moviedb.R.*

class MovieTabAdapter(fragmentManager: FragmentManager):  FragmentStatePagerAdapter(fragmentManager) {
    val categories = listOf(
        "POPULAR",
        "CURRENT"
    )

    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int) =
        when(position){
            0 -> PopularMovieFragment()
            1 -> CurrentMovieFragment()
            else -> error("Position doesn't exist")
        }

    override fun getPageTitle(position: Int) = categories[position]

}