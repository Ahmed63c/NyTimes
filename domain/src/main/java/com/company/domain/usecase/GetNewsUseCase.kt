package com.company.domain.usecase

import com.company.domain.bridge.DomainInterface

class GetNewsUseCase (private val domainBridge: DomainInterface) {
    suspend operator fun invoke() = domainBridge.getRemoteNews()
}