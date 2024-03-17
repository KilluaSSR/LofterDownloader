package com.killua.mediadownloader.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Downloaded::class], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
    abstract fun downloadedImgsDao():DownloadedImgsDao
}