package com.killua.mediadownloader.ui.items.StartPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.killua.mediadownloader.navigation.AppNavigation
import javax.inject.Inject


@Composable
fun WelcomeText(){
    val Cyan = Color(79,116,199)
    val LightBlue = Color(76,145,201)
    val Purple = Color(206,102,183)
    val gradientColors = listOf(Cyan, LightBlue, Purple /*...*/)
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
        Text(
            text = "Hi there!👋",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 30.dp)
                .padding(end = 30.dp),
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            )
        )
        Text(
            text = "What do you want to \n download today?",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp),
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            )
        )
    }

}


@Composable
fun DownloadInputText(
    urlInput:String,
    onUrlChange: (String)->Unit
)
{
    OutlinedTextField(
        value = urlInput,
        onValueChange = onUrlChange,
        singleLine = true,
        label = {
            Text(text = "Input your link here.")
        },
        shape = Shapes().extraLarge,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(0.8f)
    )
}
@Composable
fun StartBotton(onClick:()->Unit){

    Button(
        onClick = onClick,
        shape = Shapes().extraLarge,
    ) {
        Text(
            fontWeight = FontWeight.ExtraBold,
            text = "Go!",
            )
    }
}
@Composable
@Preview
fun AppTopBarPreviiew(){
    AppNavigation()
}