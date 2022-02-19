package com.company.data.network

import artifact.networking.contract.config.HttpConfig
import artifact.networking.contract.config.RetrofitConfig
import artifact.networking.contract.gateway.Gateway
import artifact.networking.interceptor.AuthInterceptor
import artifact.networking.model.GatewayConfig
import com.company.data.network.config.MainHttpConfig
import com.company.data.network.config.MainRetrofitConfigs
import com.company.data.BuildConfig


object NyTimesGateway : Gateway<Service>() {
   override val gatewayConfig: GatewayConfig =
        GatewayConfig(BuildConfig.BASE_URL, "")
    override val servicesContainer: Class<Service> = Service::class.java
    override val retrofitConfig: RetrofitConfig = MainRetrofitConfigs
    override val httpConfig: HttpConfig = MainHttpConfig
    override val authInterceptor: AuthInterceptor = AuthInterceptor()
}