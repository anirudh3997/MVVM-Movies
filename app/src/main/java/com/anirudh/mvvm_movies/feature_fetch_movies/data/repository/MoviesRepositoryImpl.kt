package com.anirudh.mvvm_movies.feature_fetch_movies.data.repository

import com.anirudh.mvvm_movies.core.util.Resource
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.MoviesApi
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MovieDetailsDTO
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MoviesDTO
import com.anirudh.mvvm_movies.feature_fetch_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mvvm_movies.BuildConfig
import retrofit2.HttpException
import java.io.IOException

class MoviesRepositoryImpl(private val api: MoviesApi) : MoviesRepository {
    override fun getMoviesList(page: Int, isTrending: Boolean): Flow<Resource<MoviesDTO>> = flow {
        emit(Resource.Loading())
        try {

            val moviesData = if (isTrending) {
                api.getTrendingMoviesList(page, BuildConfig.API_KEY)
            } else {
                api.getDiscoverMoviesList(BuildConfig.API_KEY)
            }
            emit(Resource.Success(moviesData))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                )
            )
        }
    }

    override fun getMovieDetails(movieId: String): Flow<Resource<MovieDetailsDTO>> = flow {
        emit(Resource.Loading())
        try {
            val moviesData = api.getMoviesDetails(movieId, BuildConfig.API_KEY)
            emit(Resource.Success(moviesData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Oops, something went wrong!"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection."))
        }
    }
}