package com.example.movieapp.models

data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val budget: Int,
//    val homepage: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val vote_average: Float,
    val vote_count: Int,
    val title: String,
)