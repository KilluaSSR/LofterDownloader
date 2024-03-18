package com.killua.mediadownloader

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.killua.mediadownloader.navigation.AppNavigation
import com.killua.mediadownloader.ui.theme.MediaDownloaderTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var permissionDetect: PermissionDetect
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        val buildVersion = Build.VERSION.SDK_INT
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                val imagePermissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = {
                    }
                )
            val lifecycleObserver = LocalLifecycleOwner.current
            DisposableEffect(key1 = lifecycleObserver , effect = {
                val observer = LifecycleEventObserver{_,event->
                    if(event == Lifecycle.Event.ON_START && !permissionDetect.isImagePermissionGranted(buildVersion)){
                       imagePermissionLauncher.launch(
                           when(buildVersion>=33){
                               true->READ_MEDIA_IMAGES
                               false-> READ_EXTERNAL_STORAGE})
                    }
                }
                lifecycleObserver.lifecycle.addObserver(observer)
                onDispose { lifecycleObserver.lifecycle.removeObserver(observer) }
            })
            MediaDownloaderTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MediaDownloaderTheme {
        Greeting("Android")
    }
}