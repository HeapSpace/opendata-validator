package rs.opendata.validator.service

import jodd.http.HttpRequest
import jodd.http.HttpResponse
import jodd.io.FileUtil
import jodd.util.StringUtil
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Shared downloader per a resource.
 */
class Downloader {

    data class Data(val file: File?, val status: Int, val downloadTime: Long, val size: Int)

    private val map = ConcurrentHashMap<String, Data>()
    private val outFolder = "./tmp"

//    fun download(url: String): Data {
//        return map.computeIfAbsent(url) {
//            val safeName = StringUtil.removeChars(url, ":/\\?&*=").take(80)
//            val file = File("$outFolder/$safeName")
//            val status = 200
//
//            Data(file, status, 1000)
//        }
//    }

    fun download(url: String): Data {
        return map.computeIfAbsent(url) {
            val response: HttpResponse
            val timeout = 10000
            val startTime = System.currentTimeMillis()

            try {
                response = downloadResource(it, timeout)
            } catch (ignore: Exception) {
                return@computeIfAbsent Data(null, 500, timeout.toLong(), 0)
            }

            val downloadTime = System.currentTimeMillis() - startTime

            val content = response.bodyText()
            val safeName = StringUtil.removeChars(url, ":/\\?&*=").take(80)

            val file = File("$outFolder/$safeName")
            FileUtil.writeString(file, content)

            val status = response.statusCode()

            Data(file, status, downloadTime, content.length)
        }
    }

    private fun downloadResource(url: String, timeout: Int): HttpResponse {
        return HttpRequest
                .get(url)
                .connectionTimeout(timeout)
                .timeout(timeout)
                .followRedirects(true)
                .send()
    }

}