package com.codelabs.mvvm_jokesapi.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DatabaseJoke(
    @PrimaryKey
    var id: Int,
    var joke: String,
    var categories: List<String>
)


