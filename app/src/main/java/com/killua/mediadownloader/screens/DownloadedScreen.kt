package com.killua.mediadownloader.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.killua.mediadownloader.db.Downloaded
import com.killua.mediadownloader.navigation.Destinations
import com.killua.mediadownloader.ui.items.DownloadedPage.DownloadedViewModels

@Composable
fun Downloaded(viewModel: DownloadedViewModels = hiltViewModel()) {
    val downloadedToPresent = viewModel.downloaded.collectAsState(initial = emptyList()).value

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {
        items(downloadedToPresent) { downloaded ->
            DownloadedImageItems(downloaded)
        }
    }
}

@Composable
fun DownloadedImageItems(downloaded: Downloaded) {
    val context = LocalContext.current
    val openImgLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}
    Card(
        onClick = {
            val imgURI = Uri.parse(downloaded.uri)
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(imgURI,"image/*")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            openImgLauncher.launch(intent)
        },
        modifier = Modifier
            .height(230.dp)
            .padding(8.dp),
        shape = Shapes().small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Column (modifier = Modifier.padding(8.dp)){
                Image(
                    painter = rememberAsyncImagePainter(model = downloaded.uri),
                    contentDescription = downloaded.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(4.dp)
                        .heightIn(min = 150.dp, max = 150.dp)
                        .fillMaxWidth()
                )
                Text(text = downloaded.title, style = MaterialTheme.typography.bodyMedium, maxLines = 1)
                Text(text = downloaded.author, style = MaterialTheme.typography.bodySmall, maxLines = 1)
            }
        }
    }
}