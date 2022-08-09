package com.bulbul.download_retrofit_hilt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulbul.download_retrofit_hilt.di.StateDownload
import com.bulbul.download_retrofit_hilt.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: MyRepository,
    @StateDownload private val mutableLiveData: MutableStateFlow<Int>
) : ViewModel() {

    val getProgress: StateFlow<Int>
        get() = mutableLiveData
//    val getProgress: LiveData<Int> = mutableLiveData

    fun download() {
        viewModelScope.launch {

            repository.download()

        }
    }


}