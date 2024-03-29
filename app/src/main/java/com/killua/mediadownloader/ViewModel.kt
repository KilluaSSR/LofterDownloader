package com.killua.mediadownloader

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LofterDownloadViewModel @Inject constructor(
    private val imageDownloader: ImageDownloader,
    private val imageSaver: ImageSaver,
    private val imageLoader: LofterLoadData
) : ViewModel() {
    fun loadAndSaveImgs(url:String)=viewModelScope.launch {
        val fetchResult = imageLoader.fetchImgURLs(url)
        fetchResult.imgUrls.forEach { imageUrl->
            val bitmap = imageDownloader.downloadImage(imageUrl)
            bitmap?.let {
                imageSaver.saveImageToGallery(it,imageUrl,fetchResult.title,fetchResult.author)
            }
        }
    }
}