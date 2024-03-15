package com.killua.mediadownloader.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Downloaded(
    @PrimaryKey val link: String,
    @ColumnInfo(name = "date") val date:Long
)