package com.example.movieapp.network.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("budget")
    val budget: Int,
//    @SerializedName("homepage")
//    val homepage: String,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("vote_average")
    val vote_average: Float,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("title")
    val title: String,
//    @SerializedName("belongs_to_collection")
//    val backdrop_path: String,
)