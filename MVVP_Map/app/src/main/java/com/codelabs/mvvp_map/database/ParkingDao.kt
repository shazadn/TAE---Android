package com.codelabs.mvvp_map.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ParkingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters : List<DatabaseParking>)

    @Query("SELECT * FROM DatabaseParking")
    fun getLocalDBParkingLoc(): LiveData<List<DatabaseParking>>

}

@Database(entities = [DatabaseParking::class], version  = 2, exportSchema = false)
abstract class ParkingDatabase: RoomDatabase(){
    abstract val parkingDao: ParkingDao
}
