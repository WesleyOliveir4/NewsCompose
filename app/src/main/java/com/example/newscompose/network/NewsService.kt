package com.example.newscompose.network

import com.example.newscompose.model.News
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("/svc/mostpopular/v2/emailed/1.json?api-key=${Constants.KEY_API}")
    suspend fun getMostPopularNews(): Response<News>

}