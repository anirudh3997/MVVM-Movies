package com.anirudh.mvvm_movies.feature_fetch_movies.di

import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.MoviesApi
import com.anirudh.mvvm_movies.feature_fetch_movies.data.repository.MoviesRepositoryImpl
import com.anirudh.mvvm_movies.feature_fetch_movies.domain.repository.MoviesRepository
import com.anirudh.mvvm_movies.feature_fetch_movies.domain.use_case.GetMovieDetailsUseCase
import com.anirudh.mvvm_movies.feature_fetch_movies.domain.use_case.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesAppModule {

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(repository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MoviesRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(api: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideApi(): MoviesApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(MoviesApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }
}