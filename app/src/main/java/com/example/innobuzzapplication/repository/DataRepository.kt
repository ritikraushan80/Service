package com.example.innobuzzapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.innobuzzapplication.apiService.ApiInterface
import com.example.innobuzzapplication.model.ApiData

class DataRepository(private  val apiInterface: ApiInterface) {
    private  val sourceLiveData = MutableLiveData<ArrayList<ApiData>>()

    val data: LiveData<ArrayList<ApiData>>
    get() = sourceLiveData

    suspend fun getData(){

        val result = apiInterface.getPostData()
        if (result.body() != null){
            sourceLiveData.postValue(result.body())
        }
    }
}