package com.example.movieapp.network.myResponse

import com.google.gson.annotations.SerializedName

data class MyItemResponse<T> (@SerializedName("data")
                              val data: T?)