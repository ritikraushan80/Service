package com.example.innobuzzapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innobuzzapplication.repository.DataRepository

class ApiDataViewModelFactory(private val dataRepository: DataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DataViewModel(dataRepository) as T
    }
}