package com.killua.mediadownloader.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.killua.mediadownloader.LofterDownloadViewModel
import com.killua.mediadownloader.LofterLoadData
import com.killua.mediadownloader.ui.items.StartPage.DownloadInputText
import com.killua.mediadownloader.ui.items.StartPage.StartBotton
import com.killua.mediadownloader.ui.items.StartPage.WelcomeText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//lofterDownloadViewModel: LofterDownloadViewModel = hiltViewModel()
@Composable
fun Start(lofterDownloadViewModel: LofterDownloadViewModel = hiltViewModel()){
    val localContext = LocalContext.current
    var urlInput by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            WelcomeText()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                DownloadInputText(urlInput = urlInput) { urlInput = it }
                StartBotton {
                    keyboardController?.hide()
                    if(urlInput.isEmpty()){
                        Toast.makeText(localContext,"Please enter someting before clicking me",Toast.LENGTH_SHORT).show()
                    }else{
                        coroutineScope.launch {
                            val currentInput = urlInput
                            lofterDownloadViewModel.loadAndSaveImgs(urlInput)
                            //urlInput = ""
                        }

                    }

                }

            }
        }


    }

}