package com.bulbul.download_retrofit_hilt

import com.bulbul.download_retrofit_hilt.di.StateDownload
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ProgressDownloadImp @Inject constructor(
    @StateDownload private val mutableLiveData: MutableStateFlow<Int>
) : ProgressDownload {


    override  fun update(present: Int) {
        runBlocking {
            mutableLiveData.emit(present)
        }

    }
}