package com.anirudh.mvvm_movies.feature_fetch_movies.presentation

sealed class Screen(val route: String) {
    object MovieListScreen : Screen("movie_list")
    object MovieDetailsScreen : Screen("movie_detail_screen")
}