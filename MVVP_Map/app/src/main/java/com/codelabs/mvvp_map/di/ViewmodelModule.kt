package com.codelabs.mvvp_map.di

import com.codelabs.mvvp_map.viewmodel.ParkingListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { ParkingListViewModel(get()) }
}
