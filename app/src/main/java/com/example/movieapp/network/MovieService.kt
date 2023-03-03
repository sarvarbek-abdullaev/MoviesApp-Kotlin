package com.example.movieapp.network

import com.example.movieapp.network.movie.MovieResponse
import com.example.movieapp.network.myResponse.MyItemResponse
import com.example.movieapp.network.myResponse.MyListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("discover/movie")
    suspend fun getAllMovies(
        @Query("sort_by") sort_by: String,
        @Query("page") page: String,
        @Query("api_key") api_key: String
    ): MyListResponse<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getOneMovieById(
        @Path("movie_id") record_id: String,
        @Query("api_key") api_key: String
    ): MyItemResponse<MovieResponse>
}