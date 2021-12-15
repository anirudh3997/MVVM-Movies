package com.anirudh.mvvm_movies.feature_fetch_movies.domain.use_case

import com.anirudh.mvvm_movies.core.util.Resource
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MoviesDTO
import com.anirudh.mvvm_movies.feature_fetch_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(private val repository: MoviesRepository) {
    operator fun invoke(page: Int, isTrending: Boolean): Flow<Resource<MoviesDTO>> =
        repository.getMoviesList(page, isTrending)
}