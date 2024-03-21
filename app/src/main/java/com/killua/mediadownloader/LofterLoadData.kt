package com.killua.mediadownloader

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import java.util.HashSet
import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton
data class FetchResult(
    val imgUrls:Set<String>,
    val title:String?,
    val author:String?
)
@Singleton
class LofterLoadData @Inject constructor(private val context: Context)
{
    suspend fun fetchImgURLs(url: String): FetchResult  = withContext(Dispatchers.IO) {
        val resultSet = HashSet<String>()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
            .addHeader("Referer", url)
            .build()
        var titleValue:String?=null
        var authorValue:String?=null
        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                val responseBody = response.body?.string() ?: ""
                val downloadLinkPattern = Pattern.compile("""bigimgsrc="(.*?)(?=\?imageView)""")
                val titlePattern = Pattern.compile("""<meta\s+name="Description"\s+content="([^"]+)"/?>""")
                val authorPattern = Pattern.compile("ContextValue:'&copy;&nbsp;([^']+)'")
                val authorMatcher = authorPattern.matcher(responseBody)
                val titleMatcher = titlePattern.matcher(responseBody)
                titleValue = if (titleMatcher.find()) titleMatcher.group(1) else null
                authorValue = if (authorMatcher.find()) authorMatcher.group(1) else null
                val downloadLinkMatcher = downloadLinkPattern.matcher(responseBody)
                while (downloadLinkMatcher.find()) {
                    downloadLinkMatcher.group(1)?.let { resultSet.add(it) }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext FetchResult(resultSet, title = titleValue, author = authorValue)
    }
}