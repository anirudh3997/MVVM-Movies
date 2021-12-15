package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anirudh.mvvm_movies.core.Constants
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.Movies
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.Screen

@ExperimentalFoundationApi
@Composable
fun PostersGrid(
    items: List<Movies>,
    navController: NavController,
) {
    Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceEvenly) {
        for (item in items) {
            item.posterPath?.let {
                Poster(
                    url = it,
                    contentDescription = Constants.MOVIE_POSTER,
                    modifier = Modifier
                        .width(130.dp)
                        .padding(16.dp)
                        .clickable {
                            navController.navigate(Screen.MovieDetailsScreen.route + "?movieId=${item.id}}")
                        },
                )
            }

        }
    }
}