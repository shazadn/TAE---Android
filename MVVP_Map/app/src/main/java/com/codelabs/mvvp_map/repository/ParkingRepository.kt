package com.codelabs.mvvp_map.repository

import androidx.lifecycle.LiveData
import com.codelabs.mvvp_map.database.DatabaseParking
import com.codelabs.mvvp_map.database.ParkingDatabase
import com.codelabs.mvvp_map.network.Parking_APIServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 1 Perform network request : Coroutines
 * 2 When we get the response; we will save it in local database
 *
 */
class ParkingRepository(
    private val parkingApiservices: Parking_APIServices,
    private val database: ParkingDatabase
) {
    suspend fun refreshParking() {
        // worker thread to perform API request and saving data locally
        withContext(Dispatchers.IO) {
            val parkingList = parkingApiservices.getParkingList().await()
            database.parkingDao.insertAll(parkingList)
        }
    }

    val results: LiveData<List<DatabaseParking>> = database.parkingDao.getLocalDBParkingLoc()

}