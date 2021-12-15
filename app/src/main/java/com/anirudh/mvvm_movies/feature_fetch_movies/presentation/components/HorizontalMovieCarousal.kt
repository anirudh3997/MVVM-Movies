package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.anirudh.mvvm_movies.core.Constants
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.moviesListScreen.MoviesState
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@ExperimentalSnapperApi
@ExperimentalFoundationApi
@Composable
fun HorizontalMovieCarousal(state: MoviesState) {
    val lazyListState = rememberLazyListState()

    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(lazyListState),
    ) {
        items(
            items = state.discoverMoviesList,
            itemContent = { item ->
                item.backdropPath?.let {
                    Banner(
                        url = it,
                        contentDescription = Constants.MOVIE_POSTER,
                        modifier = Modifier
                            .width(350.dp)
                            .padding(16.dp),
                        contentScale = ContentScale.FillWidth
                    )
                }

            }
        )
    }
}