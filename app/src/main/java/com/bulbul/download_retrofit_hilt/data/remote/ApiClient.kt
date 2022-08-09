package com.bulbul.download_retrofit_hilt.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiClient {
    @GET("wp-content/uploads/2021/07/74673488_l-scaled.jpeg")
    suspend fun download() : ResponseBody
}
//-1 means that web server doesnt give you any information about length of file.
