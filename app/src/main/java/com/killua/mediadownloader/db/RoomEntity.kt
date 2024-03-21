package com.killua.mediadownloader.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Downloaded(
    @PrimaryKey(autoGenerate = true) val id:Int  = 0,
    @ColumnInfo(name = "title")  val title: String,
    @ColumnInfo(name = "url") val url:String,
    @ColumnInfo(name = "author") val author:String,
    @ColumnInfo(name = "uri") val uri:String,
    @ColumnInfo(name = "date") val date:Long
)