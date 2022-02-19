package com.company.domain.bridge

import com.company.domain.model.WrapperResponse
import retrofit2.Response


interface DomainInterface {
    suspend fun getRemoteNews() : Response<WrapperResponse>
}