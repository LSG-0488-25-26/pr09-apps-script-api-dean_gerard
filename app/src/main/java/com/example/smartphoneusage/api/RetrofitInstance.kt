package com.example.smartphoneusage.api

import com.example.smartphoneusage.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val BASE_URL = BuildConfig.BASE_URL

    val api: SmartphoneApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SmartphoneApiService::class.java)
    }
}