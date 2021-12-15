package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.moviesListScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anirudh.mvvm_movies.core.Constants.PAGE_SIZE
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components.HorizontalMovieCarousal
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components.PostersGrid
import dev.chrisbanes.snapper.ExperimentalSnapperApi

@ExperimentalSnapperApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MoviesListScreen(
    navController: NavController,
    viewModel: MoviesListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Movies",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                HorizontalMovieCarousal(state = state)
            }

        }
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Now Trending",
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        // Turning the list in a list of lists of 3 elements each
        itemsIndexed(state.trendingMoviesList.windowed(3, 3, true)) { index, item ->
            viewModel.onChangeScrollPosition(index)
            if ((index) >= ((state.page - 1) * PAGE_SIZE) && !state.isLoading) {
                viewModel.nextPage()
            }
            PostersGrid(item, navController)
        }
    }


}