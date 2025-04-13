package com.example.newscompose.ui.feature.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.newscompose.network.NewsApi
import com.example.newscompose.repositories.NewsRepository
import com.example.newscompose.ui.feature.news.state.NewsUIState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class NewsViewModel(private val repository: NewsRepository): ViewModel() {
    private val _newsUiState = MutableLiveData<NewsUIState>()
    val newsUIState: LiveData<NewsUIState>
        get() = _newsUiState


    fun fetchNews() {
        viewModelScope.launch {
            _newsUiState.value = NewsUIState.Loading
            try {
                val results = viewModelScope.async { repository.fetchAllNews() }.await()
                _newsUiState.value = NewsUIState.Success(results)
            } catch (e: Exception) {
                _newsUiState.value = NewsUIState.Error(e.message ?: "Unknown Error occurred")
            }
        }
    }



// Utilizar o Koin para criar a instancia do viewmodel
    @Suppress("UNCHECKED_CAST")
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                return NewsViewModel(NewsRepository(NewsApi())) as T
            }
        }
    }
}