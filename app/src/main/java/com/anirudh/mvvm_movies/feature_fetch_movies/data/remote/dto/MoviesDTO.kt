package com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MoviesDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<Movies>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)