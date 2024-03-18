package com.killua.mediadownloader

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.HashSet
import java.util.regex.Pattern
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LofterLoadData @Inject constructor(private val context: Context)
{
    suspend fun fetchImgURLs(url:String):Set<String>{
        val resultSet = HashSet<String>()
        withContext(Dispatchers.IO){
            var link = URL(url)
            val connection = link.openConnection() as HttpURLConnection
            try {
                val response = StringBuilder()
                connection.apply {
                    setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36")
                    setRequestProperty("Referer", link.toString())
                    requestMethod = "GET"
                    connectTimeout = 8000
                    readTimeout = 8000
                }
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.apply {
                    forEachLine {
                        response.append(it)
                    }
                }
                val pattern = Pattern.compile("""bigimgsrc="(.*?)(?=\?imageView)""")
                val matcher = pattern.matcher(response.toString())
                while (matcher.find()) {
                    matcher.group(1)?.let { resultSet.add(it) }
                }
            }catch (e:Exception){
                e.printStackTrace()
            }finally {
                connection.disconnect()
            }
        }
        return resultSet
    }

}