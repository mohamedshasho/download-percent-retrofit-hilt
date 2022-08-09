package com.bulbul.download_retrofit_hilt.domain.repository


interface MyRepository {
    suspend fun download()
}