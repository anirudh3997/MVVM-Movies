package com.anirudh.mvvm_movies.feature_fetch_movies.domain.repository

import com.anirudh.mvvm_movies.core.util.Resource
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MovieDetailsDTO
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MoviesDTO
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMoviesList(page: Int, isTrending: Boolean): Flow<Resource<MoviesDTO>>
    fun getMovieDetails(movieId: String) : Flow<Resource<MovieDetailsDTO>>
}