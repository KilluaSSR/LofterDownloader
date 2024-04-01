package com.killua.mediadownloader.ui.items.DownloadedPage

import androidx.lifecycle.ViewModel
import com.killua.mediadownloader.db.Downloaded
import com.killua.mediadownloader.db.DownloadedImgsDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class DownloadedViewModels @Inject constructor(downloadedImgsDao: DownloadedImgsDao) :ViewModel(){
    val downloaded : Flow<List<Downloaded>> = downloadedImgsDao.getAll()

}