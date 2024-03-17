package com.killua.mediadownloader

import android.app.Application
import androidx.room.Room
import com.killua.mediadownloader.db.AppDatabase
import com.killua.mediadownloader.db.Downloaded
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MediaDownloaderApp : Application(){
//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java,
//            "DownloadedLinks.db"
//        ).build()
//        val downloadedDao = db.downloadedImgsDao()
//        val links :List<Downloaded> = downloadedDao.getAll()
}