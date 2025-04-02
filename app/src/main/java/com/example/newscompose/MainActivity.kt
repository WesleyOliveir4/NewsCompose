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
import com.example.newscompose.ui.feature.NewsScreen
import com.example.newscompose.ui.theme.White
import dev.tontech.news_app_yt.viewModels.NewsUIState
import dev.tontech.news_app_yt.viewModels.NewsViewModel

data class Suggestion(val title: String = "", var isFocused: Boolean = false)

object SuggestionsList {
    val list: List<Suggestion> = listOf(
        Suggestion("All", true),
        Suggestion("Politic"),
        Suggestion("Sport"),
        Suggestion("Education"),
        Suggestion("Economy"),
        Suggestion("Technology")
    )
}

class MainActivity : ComponentActivity() {
    private val vm: NewsViewModel by viewModels { NewsViewModel.Factory }
    private val state = mutableStateOf<NewsUIState>(NewsUIState.Loading)

    override fun onCreate(savedInstanceState: Bundle?) {
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
