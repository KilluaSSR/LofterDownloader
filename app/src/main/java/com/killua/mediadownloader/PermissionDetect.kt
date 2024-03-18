package com.killua.mediadownloader

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PermissionDetect @Inject constructor(private val context:Context){
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun isImagePermissionGranted(buildVersion:Int):Boolean{
        return ContextCompat.checkSelfPermission(
            context,
            when(buildVersion>=33){
                true-> READ_MEDIA_IMAGES
                false -> READ_EXTERNAL_STORAGE
            }
        )==PackageManager.PERMISSION_GRANTED
    }
}