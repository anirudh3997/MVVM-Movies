package com.anirudh.mvvm_movies.feature_fetch_movies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)