package com.example.innobuzzapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.innobuzzapplication.apiService.Retrofit
import com.example.innobuzzapplication.model.ApiData
import com.example.innobuzzapplication.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(private  val repository: DataRepository): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getData()
        }
    }

    val data: LiveData<ArrayList<ApiData>>
    get() = repository.data


}