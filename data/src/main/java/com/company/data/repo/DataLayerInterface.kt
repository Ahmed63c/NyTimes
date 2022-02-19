package com.company.data.repo

import com.company.domain.model.WrapperResponse
import retrofit2.Response

interface DataLayerInterface {
    suspend fun getNews(): Response<WrapperResponse>

}