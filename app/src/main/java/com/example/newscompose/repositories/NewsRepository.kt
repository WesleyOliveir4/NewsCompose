package com.example.newscompose.repositories

import com.example.newscompose.model.News
import com.example.newscompose.network.NewsApi
import com.example.newscompose.network.NewsService

class NewsRepository(
    private val api: NewsApi
): NewsRepositoryInterface  {
    override suspend fun fetchAllNews(): News {
        try {
            return api.getApiNews().getMostPopularNews().body()!!
        }catch(e: Exception){
            throw e
        }
    }
}