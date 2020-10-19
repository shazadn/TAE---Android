package com.codelabs.mvvp_map

import android.app.Application
import com.codelabs.mvvp_map.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MapApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MapApp)
            androidLogger(Level.DEBUG)
            modules(viewModelModule, repositoryModule, netModule, apiModule, databaseModule )
        }
    }
}