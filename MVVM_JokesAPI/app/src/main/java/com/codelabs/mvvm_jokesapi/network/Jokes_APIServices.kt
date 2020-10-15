package com.codelabs.mvvm_jokesapi.network

import com.codelabs.mvvm_jokesapi.database.DatabaseJoke
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Jokes_APIServices {
    @GET(API_Calls.API_JOKE_LIST)
    fun getJoke(): Deferred<List<DatabaseJoke>>
}