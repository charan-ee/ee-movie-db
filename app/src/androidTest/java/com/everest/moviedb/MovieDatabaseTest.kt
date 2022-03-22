package com.everest.moviedb

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.everest.moviedb.model.MovieDao
import com.everest.moviedb.model.MovieDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MovieDatabaseTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: MovieDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        movieDao = db.movieDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }



}