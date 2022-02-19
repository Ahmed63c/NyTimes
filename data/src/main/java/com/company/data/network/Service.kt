package com.company.data.network

import com.company.domain.model.WrapperResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("v2/viewed/7.json")
    suspend fun getNews(
        @Query("api-key")  apiKey:String
    ): Response<WrapperResponse>


}