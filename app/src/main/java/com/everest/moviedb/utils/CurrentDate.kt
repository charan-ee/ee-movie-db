package com.everest.moviedb.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class CurrentDate {
    @SuppressLint("SimpleDateFormat")
    val sdf = SimpleDateFormat("yyyy/MM/dd")
    private val c: Calendar = Calendar.getInstance()

    fun getCurrentDate(): String {
        return sdf.format(c.time)
    }
}