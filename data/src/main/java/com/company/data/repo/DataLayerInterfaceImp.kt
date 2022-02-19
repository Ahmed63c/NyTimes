package com.company.data.repo

import com.company.data.BuildConfig
import com.company.data.network.NyTimesGateway
import com.company.domain.model.WrapperResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DataLayerInterfaceImp :DataLayerInterface{

    //var mNyTimesAuthDaemon=NyTimesAuthDaemon.init()
    val apiManager = NyTimesGateway.invocationObject
    override suspend fun getNews(): Response<WrapperResponse> =
        withContext(Dispatchers.IO) {
            apiManager.getNews(BuildConfig.apiKey)
        }



}