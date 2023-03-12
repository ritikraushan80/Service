package com.example.innobuzzapplication.apiService

import com.example.innobuzzapplication.model.ApiData
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("posts")
    suspend fun getPostData(): Response<ArrayList<ApiData>>
}