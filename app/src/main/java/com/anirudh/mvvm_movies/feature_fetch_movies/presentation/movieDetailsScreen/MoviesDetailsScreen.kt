package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.movieDetailsScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.anirudh.mvvm_movies.core.Constants
import com.anirudh.mvvm_movies.core.Constants.MOVIE_BG
import com.anirudh.mvvm_movies.core.util.formatTime
import com.anirudh.mvvm_movies.core.util.toHoursAndMins
import com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto.MovieDetailsDTO
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components.Banner
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components.Chip
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components.CustomButton
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components.Poster
import com.anirudh.mvvm_movies.ui.theme.BlueTransparent
import com.anirudh.mvvm_movies.ui.theme.Gold

@Composable
fun MoviesDetailsScreen(
    navController: NavHostController,
    viewModel: MoviesDetailsViewModel = hiltViewModel(),
) {
    Column {
        HeaderContent(navController, viewModel.state.value.moviesDetailsDTO)
        SynopsisContent(viewModel.state.value.moviesDetailsDTO.overview)
    }
}


@Composable
fun HeaderContent(navController: NavHostController, movieDetailsDTO: MovieDetailsDTO) {
    Box(modifier = Modifier.fillMaxHeight(fraction = 0.4f)
        .background(color = MaterialTheme.colors.background)) {
        Box(modifier = Modifier.fillMaxHeight(fraction = 0.9f)
            .background(color = MaterialTheme.colors.background)) {
            movieDetailsDTO.backdropPath?.let {
                Banner(
                    url = it,
                    contentDescription = MOVIE_BG,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier.fillMaxSize().background(color = BlueTransparent),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navController.navigateUp()
                            }
                    )
                    Text(
                        text = "Movies",
                        modifier = Modifier
                            .padding(16.dp),
                        color = Color.White
                    )
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                Toast.makeText(navController.context,
                                    "Share Clicked",
                                    Toast.LENGTH_SHORT).show()
                            }
                    )
                }
                Row(modifier = Modifier
                    .padding(16.dp, 16.dp)
                ) {
                    movieDetailsDTO.posterPath?.let {
                        Poster(
                            url = it,
                            contentDescription = Constants.MOVIE_POSTER,
                            modifier = Modifier
                                .width(150.dp)
                        )
                    }
                    Column(modifier = Modifier
                        .padding(8.dp, 0.dp, 0.dp, 0.dp)
                    ) {
                        Text(
                            text = movieDetailsDTO.title,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            if (movieDetailsDTO.adult) {
                                Text(
                                    text = "R",
                                    color = Color.White
                                )
                            } else {
                                Text(
                                    text = "U",
                                    color = Color.White
                                )
                            }
                            Text(
                                text = " | " + movieDetailsDTO.runtime.toHoursAndMins(),
                                color = Color.White
                            )
                            Text(
                                text = " | " + movieDetailsDTO.releaseDate.formatTime(),
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            for (genre in movieDetailsDTO.genres) {
                                Chip(name = genre.name)
                                Spacer(modifier = Modifier.width(5.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            for (i in 0 until 5) {
                                val iconColor = if (i < (movieDetailsDTO.voteAverage / 2).toInt()) {
                                    Gold
                                } else {
                                    Color.Gray
                                }
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    tint = iconColor,
                                    contentDescription = "Back",
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clickable {
                                        }
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "" + movieDetailsDTO.voteAverage,
                                color = Color.White
                            )

                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.BottomEnd).padding(0.dp, 0.dp, 16.dp, 10.dp)) {
            CustomButton(modifier = Modifier.clickable {
                Toast.makeText(navController.context, "Book Now Clicked", Toast.LENGTH_SHORT).show()
            })
        }
    }

}

@Composable
fun SynopsisContent(content: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Synopsis",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = content
        )
    }
}
