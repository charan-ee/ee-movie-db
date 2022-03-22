package com.everest.moviedb.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDao: MovieDao

    private var INSTANCE: MovieDatabase? = null

    fun getInstance(context: Context): MovieDatabase {
        synchronized(this) {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_list_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

}