package com.android.devlifes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.devlifes.api.Repository
import com.android.devlifes.model.Gif
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<Gif>> = MutableLiveData()

    fun getGif(){
        viewModelScope.launch {
            val response = repository.getGif()
            myResponse.value = response
        }
    }
}