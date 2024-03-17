package com.killua.mediadownloader.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DownloadedImgsDao{
    @Query("Select * FROM Downloaded")
    fun getAll():List<Downloaded>
    @Insert
    fun insertAll(vararg links:Downloaded)
    @Delete
    fun delete(links: Array<out Downloaded>)
}