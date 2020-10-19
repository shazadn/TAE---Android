package com.codelabs.mvvp_map.network

import com.codelabs.mvvp_map.database.DatabaseParking
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Parking_APIServices {
    @GET(API_Calls.API_PARKING_LIST)
    fun getParkingList(): Deferred<List<DatabaseParking>>
}