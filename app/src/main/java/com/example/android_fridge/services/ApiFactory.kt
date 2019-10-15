package com.example.android_fridge.services

import com.example.android_fridge.utilities.AppConstants

object ApiFactory {

    val foodsApi : FoodsApi = RetrofitFactory.retrofit(AppConstants().BASE_URL)
        .create(FoodsApi::class.java)
}