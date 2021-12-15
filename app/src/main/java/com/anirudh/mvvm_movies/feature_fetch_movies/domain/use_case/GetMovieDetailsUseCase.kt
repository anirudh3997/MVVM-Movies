package com.anirudh.mvvm_movies.feature_fetch_movies.domain.use_case

import com.anirudh.mvvm_movies.core.util.Resource
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MovieDetailsDTO
import com.anirudh.mvvm_movies.feature_fetch_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val repository: MoviesRepository) {
    operator fun invoke(movieId: String): Flow<Resource<MovieDetailsDTO>> =
        repository.getMovieDetails(movieId)
}