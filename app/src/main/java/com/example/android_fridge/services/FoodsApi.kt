package com.example.android_fridge.services

import com.example.android_fridge.data.ItemResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface FoodsApi {
    @GET("foodoptions")
    suspend fun getFoodOptions(): Response<ItemResponse>
}