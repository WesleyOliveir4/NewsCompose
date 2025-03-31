package com.example.newscompose.repositories

import com.example.newscompose.model.News

interface NewsRepositoryInterface {
    suspend fun fetchAllNews(): News
}