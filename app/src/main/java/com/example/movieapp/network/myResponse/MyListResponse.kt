package com.example.movieapp.network.myResponse

import com.google.gson.annotations.SerializedName

class MyListResponse<T> (@SerializedName("results")
                         val results: List<T>?)