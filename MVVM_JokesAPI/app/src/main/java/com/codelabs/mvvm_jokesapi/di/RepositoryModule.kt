package com.codelabs.mvvm_jokesapi.di

import com.codelabs.mvvm_jokesapi.database.JokeDatabase
import com.codelabs.mvvm_jokesapi.network.Jokes_APIServices
import com.codelabs.mvvm_jokesapi.repository.JokeRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideRepository(api: Jokes_APIServices, dao: JokeDatabase): JokeRepository {
        return JokeRepository(api, dao)
    }

    single {
        provideRepository(get(), get())
    }
}