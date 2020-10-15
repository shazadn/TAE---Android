package com.codelabs.mvvm_jokesapi

import android.app.Application
import com.codelabs.mvvm_jokesapi.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class JokeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JokeApp)
            androidLogger(Level.DEBUG)
            modules(viewModelModule, repositoryModule, netModule, apiModule, databaseModule)
        }
    }
}