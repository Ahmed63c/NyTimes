package com.company.data.repo

import com.company.domain.bridge.DomainInterface
import com.company.domain.model.WrapperResponse
import retrofit2.Response

class DomainInterfaceImp(
    private val dataLayerInterface: DataLayerInterface
) : DomainInterface {
    override suspend fun getRemoteNews(): Response<WrapperResponse> {
        return dataLayerInterface.getNews()
    }

}