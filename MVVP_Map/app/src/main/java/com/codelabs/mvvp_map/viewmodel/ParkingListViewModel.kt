package com.codelabs.mvvp_map.viewmodel

import androidx.lifecycle.ViewModel
import com.codelabs.mvvp_map.repository.ParkingRepository
import kotlinx.coroutines.*
import java.lang.Exception

class ParkingListViewModel(private val parkingRepository: ParkingRepository): ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope  = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * A list of parking locations displayed on the screen.
     */
    val parkingListResults = parkingRepository.results

    init {
        refreshFromRepository()
    }

    fun refreshFromRepository(){
        viewModelScope.launch {
            try {
                parkingRepository.refreshParking()
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