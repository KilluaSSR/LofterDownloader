package com.killua.mediadownloader.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItems(
    val label: String,
    val icon: ImageVector,
    val route: String
)
val listOfNavItems = listOf(
    NavItems(
        label = "Start",
        icon = Icons.Default.Home,
        route = Destinations.Start.name
    ),
    NavItems(
        label = "Downloaded",
        icon = Icons.Default.Done,
        route = Destinations.Downloaded.name
    ),
    NavItems(
        label = "Settings",
        icon = Icons.Default.Settings,
        route = Destinations.Settings.name
    )
)
