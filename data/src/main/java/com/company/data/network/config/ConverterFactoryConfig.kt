package com.company.data.network.config

import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory


object ConverterFactoryConfig {

    fun getConverterFactory(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder()
            .registerTypeAdapters()
            .create()
    )

    private fun GsonBuilder.registerTypeAdapters(): GsonBuilder {

        return this
    }
}