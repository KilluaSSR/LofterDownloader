package com.killua.mediadownloader.di

import android.content.Context
import androidx.room.Room
import com.killua.mediadownloader.db.AppDatabase
import com.killua.mediadownloader.db.DownloadedImgsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvider {
    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"Downloaded").build()
    }
    @Provides
    fun provideDownloadedImgsDao(appDatabase: AppDatabase):DownloadedImgsDao{
        return appDatabase.downloadedImgsDao()
    }
}