package com.killua.mediadownloader.ui.items.SettingsPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch
import com.killua.mediadownloader.screens.Settings



@Composable
fun GeneralOptionsUI(){
    Column (
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .padding(top = 10.dp)
    ){
        GeneralSettingsItem()
        ProfileCardUI()
    }
}
@Composable
fun ProfileCardUI(){
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .height(450.dp)
            .padding(10.dp),
        shape = Shapes().large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Nice to meet you! \n Still Building ⚠️")
            }
        }
    }
}
@Composable
fun GeneralSettingsItem() {
    var backgruondDownloadSwitch by remember {
        mutableStateOf(false)
    }
    Column (modifier = Modifier.fillMaxWidth()){
        SettingsSwitch(
            title = { Text(text = "Background Download") },
            subtitle = { Text(text = " Automatically download imgs by listening to your clipboard") },
            modifier = Modifier,
            enabled = true,
            icon = {  },
            state = backgruondDownloadSwitch,
            onCheckedChange = { newState: Boolean -> backgruondDownloadSwitch = !backgruondDownloadSwitch },
        )
    }
}
//@Composable
//fun DropdownMenuExample() {
//    var expanded by remember { mutableStateOf(false) }
//    var selectedOption by remember { mutableStateOf("") }
//    val items = listOf("Option 1", "Option 2", "Option 3")
//
//    Button(onClick = { expanded = true }) {
//        Text("Show Menu")
//    }
//
//    DropdownMenu(
//        expanded = expanded,
//        onDismissRequest = { expanded = false }
//    ) {
//        items.forEach { label ->
//            DropdownMenuItem(
//                onClick = {
//                    expanded = false
//                    selectedOption = label
//                },
//                text = { Text(text = label)}
//            )
//        }
//    }
//
//    if (selectedOption.isNotEmpty()) {
//        Text(text = "You selected: $selectedOption")
//    }
//}

@Composable
@Preview
fun Display(){
    GeneralSettingsItem()
}

