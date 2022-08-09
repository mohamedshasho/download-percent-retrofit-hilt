package com.bulbul.download_retrofit_hilt

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*


class ProgressDownloadBody(
    private val responseBody: ResponseBody,
    private val progressDownload: ProgressDownload
) : ResponseBody() {

    companion object {
        lateinit var bufferedSource: BufferedSource
    }

    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    override fun source(): BufferedSource {
        bufferedSource = Okio.buffer(source(responseBody.source()))
        return bufferedSource
    }


    private fun source(source: Source): Source {
        return object : ForwardingSource(source) {
            var totalBytesRead = 0L
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)
                totalBytesRead += if (bytesRead != -1L) bytesRead else 0
                val percent =
                    if (bytesRead == -1L) 100 else (totalBytesRead / contentLength().toFloat() * 100).toInt()
                progressDownload.update(percent)
                return bytesRead
            }
        }
    }
}