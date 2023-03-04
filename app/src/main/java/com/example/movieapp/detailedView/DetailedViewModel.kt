package com.example.movieapp.detailedView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.Movie
import com.example.movieapp.network.RetrofitInstance
import com.example.movieapp.network.movie.MovieResponse
import com.example.movieapp.utils.Constants
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailedViewModel(mealId: String) : ViewModel() {

    val mealLiveData: MutableLiveData<Movie> by lazy {
        MutableLiveData<Movie>()
    }

    init {
        getMealByIdFromRemoteDb(mealId)
    }

    private fun getMealByIdFromRemoteDb(movieId: String) {
        viewModelScope.launch {
            try {
                val response: MovieResponse =
                    RetrofitInstance.movieService.getOneMovieById(movieId, Constants.APIKEY)
                val mealFromResponse = response

                if (mealFromResponse != null) {
                    mealLiveData.value = Movie(
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
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}