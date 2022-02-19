package com.company.data.network.config

import artifact.networking.ConnectionDefaults
import artifact.networking.HeaderKeys
import artifact.networking.HeaderValues
import artifact.networking.contract.config.HttpConfig
import artifact.networking.interceptor.HeaderInterceptor
import okhttp3.Cache
import okhttp3.Interceptor

object MainHttpConfig : HttpConfig() {
    private val acceptHeaderInterceptor =
        HeaderInterceptor(HeaderKeys.HEADER_KEY_ACCEPT, HeaderValues.HEADER_VALUE_APPLICATION_JSON)

    override val interceptors: ArrayList<Interceptor> = arrayListOf()
    override val networkInterceptors: ArrayList<Interceptor> = arrayListOf(
        acceptHeaderInterceptor,
    )
    override val connectionTimeout: Long
        get() = ConnectionDefaults.CONNECTION_TIMEOUT
    override val readTimeout: Long
        get() = ConnectionDefaults.READ_TIMEOUT
    override val writeTimeout: Long
        get() = ConnectionDefaults.WRITE_TIMEOUT
    override val cache: Cache?
        get() = null
}