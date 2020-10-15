package com.codelabs.mvvm_jokesapi.viewmodel

import androidx.lifecycle.ViewModel
import com.codelabs.mvvm_jokesapi.repository.JokeRepository
import kotlinx.coroutines.*
import java.lang.Exception

class JokeViewModel(private val jokeRepository: JokeRepository): ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope  = CoroutineScope(viewModelJob + Dispatchers.Main)


    val jokeResults = jokeRepository.results

    init {
        refreshFromRepository()
    }

    fun refreshFromRepository(){
        viewModelScope.launch {
            try {
                jokeRepository.refreshJokes()
            }
            catch(networkError: Exception){

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}