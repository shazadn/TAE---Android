package com.codelabs.mvvp_map.di

import com.codelabs.mvvp_map.database.ParkingDatabase
import com.codelabs.mvvp_map.network.Parking_APIServices
import com.codelabs.mvvp_map.repository.ParkingRepository
import org.koin.dsl.module

val repositoryModule= module {
    fun provideRepository(api: Parking_APIServices, dao: ParkingDatabase): ParkingRepository {
        return ParkingRepository(api, dao)
    }

    single { provideRepository(get(), get())
    }
}