package com.vikash.currencyconverter

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

private val _retrofit= Retrofit.Builder().baseUrl("https://api.freecurrencyapi.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val ApiService= _retrofit.create(DataSaveHandler::class.java)

interface DataSaveHandler{
    @GET("v1/latest")
    suspend fun getCurrencyData(
        @Query("apikey") apiKey: String
    ): FinalDataHolder
}