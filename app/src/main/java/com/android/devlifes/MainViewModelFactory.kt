package com.android.devlifes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.devlifes.api.Repository

class MainViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}