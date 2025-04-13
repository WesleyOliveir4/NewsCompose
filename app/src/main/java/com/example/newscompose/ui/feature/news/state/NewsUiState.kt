package com.example.newscompose.ui.feature.news.state

import com.example.newscompose.model.News

sealed class NewsUIState {
    data object Loading: NewsUIState()
    data class Success(val news: News): NewsUIState()
    data class Error(val message: String): NewsUIState()
}