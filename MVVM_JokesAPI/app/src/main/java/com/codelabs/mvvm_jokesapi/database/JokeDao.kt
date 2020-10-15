package com.codelabs.mvvm_jokesapi.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codelabs.mvvm_jokesapi.utils.Converters

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(jokes: List<DatabaseJoke>)

    @Query("SELECT * FROM DatabaseJoke")
    fun getLocalDBJokes(): LiveData<List<DatabaseJoke>>
}

@Database(entities = [DatabaseJoke::class], version = 15, exportSchema = false)
@TypeConverters(Converters::class)
abstract class JokeDatabase : RoomDatabase() {
    abstract val jokeDao: JokeDao
}