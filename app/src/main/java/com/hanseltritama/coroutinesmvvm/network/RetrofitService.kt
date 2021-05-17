package com.hanseltritama.coroutinesmvvm.network

import com.hanseltritama.coroutinesmvvm.models.Owner
import com.hanseltritama.coroutinesmvvm.models.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("search/repositories")
    suspend fun getDataFromApi(@Query("q") query: String) : RecyclerList

    @GET("users/{user_id}")
    suspend fun getUserDataApi(@Path("user_id") user_id: String) : Owner
}