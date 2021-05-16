package com.hanseltritama.coroutinesmvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BaseURL = "https://api.github.com/search/"

    fun getRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}