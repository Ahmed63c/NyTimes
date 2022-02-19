package com.company.nytimes.di

import com.company.data.repo.DataLayerInterface
import com.company.data.repo.DataLayerInterfaceImp
import com.company.data.repo.DomainInterfaceImp
import com.company.domain.bridge.DomainInterface
import com.company.domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsModule {
    @Singleton
    @Provides
    fun provideGetNews(domainInterFace: DomainInterface): GetNewsUseCase {
        return GetNewsUseCase(domainInterFace)
    }
    @Provides
    @Singleton
    fun provideDomainInterface(dataLayerInterface: DataLayerInterface): DomainInterface {
        return DomainInterfaceImp(dataLayerInterface)
    }


    @Provides
    @Singleton
    fun provideDataLayerInterface(): DataLayerInterface {
        return DataLayerInterfaceImp()
    }

}