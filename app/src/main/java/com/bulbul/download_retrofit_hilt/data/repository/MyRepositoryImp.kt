package com.bulbul.download_retrofit_hilt.data.repository

import com.bulbul.download_retrofit_hilt.data.remote.ApiClient
import com.bulbul.download_retrofit_hilt.domain.repository.MyRepository

class MyRepositoryImp(private val api: ApiClient) : MyRepository {

    override suspend fun download() {
        val data = api.download()
    }
}