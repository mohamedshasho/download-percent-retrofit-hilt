package com.bulbul.download_retrofit_hilt

import okhttp3.Interceptor
import okhttp3.Response

class RemoteServiceInterceptor
    (private val progressDownload: ProgressDownload) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return response.newBuilder()
            .body(ProgressDownloadBody(response.body()!!, progressDownload))
            .build()
    }
}