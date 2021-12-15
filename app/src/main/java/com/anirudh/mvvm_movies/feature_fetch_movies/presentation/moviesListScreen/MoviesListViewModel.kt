package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.moviesListScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anirudh.mvvm_movies.core.Constants.PAGE_SIZE
import com.anirudh.mvvm_movies.core.util.Resource
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.Movies
import com.anirudh.mvvm_movies.feature_fetch_movies.domain.use_case.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(MoviesState())
    val state: State<MoviesState> = _state

    private var scrollPosition = 0

    private var getTrendingMoviesJob: Job? = null
    private var getDiscoverMoviesJob: Job? = null

    init {
        getTrendingMoviesList(page = state.value.page)
        getDiscoverMoviesList()
    }

    private fun getDiscoverMoviesList() {
        getDiscoverMoviesJob?.cancel()
        getDiscoverMoviesJob = viewModelScope.launch {
            getMoviesUseCase(1, false).onEach { response ->
                when (response) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            isFailed = true,
                            errorMessage = response.message.toString()
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        response.data?.let {
                            _state.value = state.value.copy(
                                discoverMoviesList = it.movies,
                                isLoading = false,
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getTrendingMoviesList(page: Int) {
        getTrendingMoviesJob?.cancel()
        getTrendingMoviesJob = viewModelScope.launch {
            getMoviesUseCase(page, true).onEach { response ->
                when (response) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            isFailed = true,
                            errorMessage = response.message.toString()
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        response.data?.let {
                            val list: ArrayList<Movies> =
                                state.value.trendingMoviesList as ArrayList<Movies>
                            list.addAll(it.movies)
                            _state.value = state.value.copy(
                                trendingMoviesList = list,
                                isLoading = false,
                            )
                        }

                    }
                }
            }.launchIn(this)
            incrementPage()
        }
    }

    fun nextPage() {
        if ((scrollPosition) >= ((state.value.page - 1) * PAGE_SIZE)) {
            getTrendingMoviesList(page = state.value.page)
        }
    }

    private fun incrementPage() {
        _state.value.page = _state.value.page + 1
    }

    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position
    }

}