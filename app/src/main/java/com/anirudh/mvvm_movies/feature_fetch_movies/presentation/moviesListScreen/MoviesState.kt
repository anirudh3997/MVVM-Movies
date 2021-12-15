package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.moviesListScreen

import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.Movies

data class MoviesState(
    var trendingMoviesList: List<Movies> = ArrayList(),
    var discoverMoviesList: List<Movies> = ArrayList(),
    var isLoading: Boolean = false,
    var isFailed: Boolean = false,
    var errorMessage: String = "",
    var page: Int = 1,
)
