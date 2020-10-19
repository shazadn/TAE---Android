package com.codelabs.mvvp_map.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseParking (
    @PrimaryKey
    var id: Int,
    var lat: Double,
    var lng: Double,
    var is_reserved: Boolean
)