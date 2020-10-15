package com.codelabs.mvvm_jokesapi.di

import android.app.Application
import androidx.room.Room
import com.codelabs.mvvm_jokesapi.database.JokeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

//Module: returns instance of the class (loose coupling)
val databaseModule = module {
    fun providesDatabase(application: Application): JokeDatabase {
        return Room.databaseBuilder(application, JokeDatabase::class.java, "jokes.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    // singleton: single instance
    single { providesDatabase(androidApplication()) } // lifecycle of the application
}