package com.killua.mediadownloader.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.killua.mediadownloader.navigation.Destinations
import com.killua.mediadownloader.ui.items.SettingsPage.GeneralOptionsUI
import com.killua.mediadownloader.ui.items.SettingsPage.ProfileCardUI

@Composable
fun Settings(){
    Box(
        modifier = Modifier.fillMaxSize(),
    )
    {
//        Text(
//            text = Destinations.Settings.name,
//            fontFamily = FontFamily.Serif,
//            fontSize = 22.sp
//        )

        GeneralOptionsUI()
    }
}