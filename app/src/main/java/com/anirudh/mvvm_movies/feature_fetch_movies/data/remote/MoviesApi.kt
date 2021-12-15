package com.anirudh.mvvm_movies.feature_fetch_movies.data.remote

import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MovieDetailsDTO
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MoviesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("/3/trending/movie/day")
    suspend fun getTrendingMoviesList(
        @Query("page")  page: Int,
        @Query("api_key") apiKey: String
    ): MoviesDTO

    @GET("/3/discover/movie")
    suspend fun getDiscoverMoviesList(
        @Query("api_key") apiKey: String
    ): MoviesDTO

    @GET("/3/movie/{movieId}")
    suspend fun getMoviesDetails(
        @Path("movieId")  movieId: String,
        @Query("api_key") apiKey: String
    ): MovieDetailsDTO

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }
}