package com.company.data.network.config

import artifact.networking.contract.config.RetrofitConfig
import com.company.data.network.config.ConverterFactoryConfig
import retrofit2.CallAdapter
import retrofit2.Converter

object MainRetrofitConfigs : RetrofitConfig() {
    override val converterFactory: Converter.Factory = ConverterFactoryConfig.getConverterFactory()
    override val callAdapterFactory: CallAdapter.Factory? = null

}