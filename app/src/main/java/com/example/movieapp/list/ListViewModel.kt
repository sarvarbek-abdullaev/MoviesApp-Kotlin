package com.example.movieapp.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Movie
import com.example.movieapp.network.RetrofitInstance
import com.example.movieapp.network.movie.MovieResponse
import com.example.movieapp.network.myResponse.MyListResponse
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.launch
import java.lang.Exception

class ListViewModel(genre: String) : ViewModel() {

    val moviesLiveData: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }

    init {
        when (genre) {
            "main" -> getListOfMealsFromRemoteDb()
            "cartoons" -> getListOfMoviesByGenres("16")
            "popular" -> getListOfMoviesByGenres("28,18")
            "movies" -> getListOfMoviesByGenres("878")
        }
    }

    fun getListOfMealsFromRemoteDb() {
        viewModelScope.launch {
            try {
                val response: MyListResponse<MovieResponse> =
                    RetrofitInstance.movieService.getAllMovies(sort_by = "popularity.desc", page = "1", api_key = Constants.APIKEY)
                val mealsFromResponse = response.results
                if (mealsFromResponse != null) {
                    val meals = mutableListOf<Movie>()
                    for (mealFromResponse in mealsFromResponse) {
                        meals.add(
                            Movie(
                                mealFromResponse.id,
                                mealFromResponse.adult,
                                mealFromResponse.backdrop_path,
                                mealFromResponse.budget,
//                                mealFromResponse.homepage,
                                mealFromResponse.original_language,
                                mealFromResponse.original_title,
                                mealFromResponse.overview,
                                mealFromResponse.popularity,
                                mealFromResponse.poster_path,
                                mealFromResponse.release_date,
                                mealFromResponse.revenue,
                                mealFromResponse.vote_average,
                                mealFromResponse.vote_count,
                                mealFromResponse.title
                            )
                        )
                    }
                    moviesLiveData.value = meals
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun getListOfMoviesByGenres(genres: String) {
        viewModelScope.launch {
            try {
                val response: MyListResponse<MovieResponse> =
                    RetrofitInstance.movieService.getMoviesByGenre(with_genres = genres, api_key = Constants.APIKEY)
                val mealsFromResponse = response.results
                if (mealsFromResponse != null) {
                    val meals = mutableListOf<Movie>()
                    for (mealFromResponse in mealsFromResponse) {
                        meals.add(
                            Movie(
                                mealFromResponse.id,
                                mealFromResponse.adult,
                                mealFromResponse.backdrop_path,
                                mealFromResponse.budget,
//                                mealFromResponse.homepage,
                                mealFromResponse.original_language,
                                mealFromResponse.original_title,
                                mealFromResponse.overview,
                                mealFromResponse.popularity,
                                mealFromResponse.poster_path,
                                mealFromResponse.release_date,
                                mealFromResponse.revenue,
                                mealFromResponse.vote_average,
                                mealFromResponse.vote_count,
                                mealFromResponse.title
                            )
                        )
                    }
                    moviesLiveData.value = meals
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}