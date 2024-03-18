package com.killua.mediadownloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageDownloader @Inject constructor() {
    suspend fun downloadImage(imageURL: String): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val url = URL(imageURL)
            (url.openConnection() as HttpURLConnection).apply {
                setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                setRequestProperty("Referer",imageURL)
                requestMethod = "GET"
                connectTimeout = 8000
                readTimeout = 8000
            }.inputStream.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}