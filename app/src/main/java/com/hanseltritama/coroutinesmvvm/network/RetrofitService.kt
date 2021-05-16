package com.hanseltritama.coroutinesmvvm.network

import com.hanseltritama.coroutinesmvvm.models.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("repositories")
    suspend fun getDataFromApi(@Query("q") query: String) : RecyclerList
}