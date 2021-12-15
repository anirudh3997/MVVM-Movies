package com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
)