package com.everest.moviedb

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MovieTabAdapter(fragmentManager: FragmentManager, private val context: Context):  FragmentStatePagerAdapter(fragmentManager) {
    val categories = listOf(
        context.resources.getString(R.string.tab_popular),
        context.resources.getString(R.string.tab_current)
    )

    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int) =
        when(position){
            0 -> PopularMovieFragment()
            1 -> CurrentMovieFragment()
            else -> error(context.resources.getString(R.string.error_no_position))
        }

    override fun getPageTitle(position: Int) = categories[position]

}