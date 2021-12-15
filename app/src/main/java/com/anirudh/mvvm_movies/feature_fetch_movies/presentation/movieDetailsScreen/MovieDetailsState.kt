package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.movieDetailsScreen

import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MovieDetailsDTO

data class MovieDetailsState(
    var moviesDetailsDTO: MovieDetailsDTO = MovieDetailsDTO(),
    var isLoading: Boolean = false,
    var isFailed: Boolean = false,
    var errorMessage: String = ""
)
