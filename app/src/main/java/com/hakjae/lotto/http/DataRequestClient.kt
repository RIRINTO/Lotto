package com.hakjae.lotto.http

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRequestClient {

    fun getLottoDataService(): GetLottoDataApi = retrofit.create(GetLottoDataApi::class.java)

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://dhlottery.co.kr/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
}