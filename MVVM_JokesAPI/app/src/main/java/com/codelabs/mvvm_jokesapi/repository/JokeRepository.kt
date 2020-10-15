package com.codelabs.mvvm_jokesapi.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.codelabs.mvvm_jokesapi.database.DatabaseJoke
import com.codelabs.mvvm_jokesapi.database.JokeDatabase
import com.codelabs.mvvm_jokesapi.network.Jokes_APIServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokeRepository(
    private val jokesApiservices: Jokes_APIServices,
    private val database: JokeDatabase
) {
    suspend fun refreshJokes() {
        // worker thread to perform API request and saving data locally
        withContext(Dispatchers.IO) {
            val joke = jokesApiservices.getJoke().await()
            database.jokeDao.insertAll(joke)
        }
    }

    val results: LiveData<List<DatabaseJoke>> = database.jokeDao.getLocalDBJokes()

}