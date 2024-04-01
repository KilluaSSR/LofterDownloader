package com.killua.mediadownloader

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.killua.mediadownloader.db.Downloaded
import com.killua.mediadownloader.db.DownloadedImgsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageSaver @Inject constructor(
    private val context: Context,
    private val downloadedImgsDao: DownloadedImgsDao,
    ) {
    suspend fun saveImageToGallery(
        bitmap: Bitmap,
        url:String,
        title:String?,
        author:String?
        ): Boolean = withContext(Dispatchers.IO) {
        val displayName = "Image_${System.currentTimeMillis()}.jpg"
        val mimeType = "image/jpeg"
        val contentValues =  ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, displayName)
            put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }
        val resolver = context.contentResolver
        var stream: OutputStream? = null
        val uri: Uri?
        try {
            val contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            uri = resolver.insert(contentUri, contentValues)
            if (uri != null) {
                stream = resolver.openOutputStream(uri)
                if (stream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.flush()
                }
                uri.let {
                    val downloadedImage = Downloaded(
                        title =  title!!,
                        date = System.currentTimeMillis(),
                        author = author!!,
                        url = url,
                        uri = uri.toString()
                    )
                    downloadedImgsDao.insertAll(downloadedImage)
                }
            }

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {
            stream?.close()
        }
    }
}
