package com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    @SerializedName("genre_ids")
    val genreIds: List<Int> = emptyList(),
    @SerializedName("title")
    val title: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("media_type")
    val mediaType: String = ""
)