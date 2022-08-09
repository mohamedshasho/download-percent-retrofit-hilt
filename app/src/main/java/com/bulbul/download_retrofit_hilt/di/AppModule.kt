package com.bulbul.download_retrofit_hilt.di

import com.bulbul.download_retrofit_hilt.*
import com.bulbul.download_retrofit_hilt.data.remote.ApiClient
import com.bulbul.download_retrofit_hilt.data.repository.MyRepositoryImp
import com.bulbul.download_retrofit_hilt.domain.repository.MyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApiClient(okHttpClient: OkHttpClient): ApiClient {
        return Retrofit.Builder()
            .baseUrl("https://www.homefortheharvest.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideMyRepository(apiClient: ApiClient): MyRepository {
        return MyRepositoryImp(apiClient)
    }

    @StateDownload
    @Provides
    @Singleton
    fun provideMutableStateFlow(): MutableStateFlow<Int> {
        return MutableStateFlow(0)
    }


    @Provides
    @Singleton
    fun provideProgressDownload(@StateDownload mutableLiveData: MutableStateFlow<Int>): ProgressDownload {
        return ProgressDownloadImp(mutableLiveData)
    }


    @Provides
    @Singleton
    fun provideOkHttp(progressDownload: ProgressDownload): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(RemoteServiceInterceptor(progressDownload))
            .build()
    }

}