package com.example.android_fridge.services

import com.google.gson.annotations.SerializedName

// This will be relevant once backend has been fleshed out a bit more

data class ResultsResponse<T> (
    @SerializedName("results")
    val temp: List<T>
)