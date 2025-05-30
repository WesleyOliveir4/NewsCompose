package com.example.newscompose.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApi {
    private lateinit var api : NewsService

    fun getApiNews(): NewsService{
        if (!::api.isInitialized){
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            api = retrofit.create(NewsService::class.java)
        }
        return api
    }
}