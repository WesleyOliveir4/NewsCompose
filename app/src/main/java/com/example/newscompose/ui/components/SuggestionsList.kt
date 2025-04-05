package com.example.newscompose.ui.components

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
