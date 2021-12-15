package com.anirudh.mvvm_movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.movieDetailsScreen.MoviesDetailsScreen
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.moviesListScreen.MoviesListScreen
import com.anirudh.mvvm_movies.feature_fetch_movies.presentation.Screen
import com.anirudh.mvvm_movies.ui.theme.MVVM_MoviesTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.snapper.ExperimentalSnapperApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalSnapperApi
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVM_MoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MovieListScreen.route
                    ) {
                        composable(route = Screen.MovieListScreen.route) {
                            MoviesListScreen(navController = navController)
                        }
                        composable(route = Screen.MovieDetailsScreen.route +
                                "?movieId={movieId}",
                            arguments = listOf(
                                navArgument(name = "movieId") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            MoviesDetailsScreen(
                                navController = navController,
                            )
                        }
                    }
                }
            }
        }
    }
}