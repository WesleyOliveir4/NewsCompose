package com.example.newscompose

import NewsappytTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newscompose.ui.feature.news.screen.NewsScreen
import com.example.newscompose.ui.feature.news.state.NewsUIState
import com.example.newscompose.ui.theme.White
import com.example.newscompose.ui.feature.news.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {
    private val vm: NewsViewModel by viewModels { NewsViewModel.Factory }
    private val state = mutableStateOf<NewsUIState>(NewsUIState.Loading)

    override fun onCreate(savedInstanceState: Bundle?) {
        // agregar o trecho do when na propia screen e criar um navhost para inicializar a screen
        vm.fetchNews()

        vm.newsUIState.observe(this) {
            state.value = it
        }

        super.onCreate(savedInstanceState)
        setContent {
            NewsappytTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = White
                ) {
                    when(val currentState = state.value) {
                        is NewsUIState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is NewsUIState.Success -> {
                            NewsScreen(news = currentState.news, viewModel = vm)
                        }
                        is NewsUIState.Error -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = currentState.message)
                            }
                        }
                    }
                }
            }
        }
    }
}
