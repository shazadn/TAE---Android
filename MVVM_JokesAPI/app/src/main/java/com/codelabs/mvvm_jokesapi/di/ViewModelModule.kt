package com.codelabs.mvvm_jokesapi.di

import com.codelabs.mvvm_jokesapi.viewmodel.JokeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { JokeViewModel(get()) }
}