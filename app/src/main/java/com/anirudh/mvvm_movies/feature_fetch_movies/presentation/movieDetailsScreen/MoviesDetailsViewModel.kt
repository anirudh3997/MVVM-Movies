package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.movieDetailsScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anirudh.mvvm_movies.core.util.Resource
import com.anirudh.mvvm_movies.feature_fetch_movies.domain.use_case.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = mutableStateOf(MovieDetailsState())
    val state: State<MovieDetailsState> = _state

    private var getDiscoverMoviesJob: Job? = null

    init {
        savedStateHandle.get<String>("movieId")?.let { movieId ->
            getMovieDetails(movieId)
        }
    }

    private fun getMovieDetails(movieId: String) {
        getDiscoverMoviesJob?.cancel()
        getDiscoverMoviesJob = viewModelScope.launch {
            getMovieDetailsUseCase(movieId).onEach { response ->
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
                            Log.e("TAG", "getMovieDetails: $it")
                            _state.value = state.value.copy(
                                moviesDetailsDTO = it,
                                isLoading = false,
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

}