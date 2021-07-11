package com.hakjae.lotto.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetLottoDataApi {

    @GET("https://dhlottery.co.kr/common.do?method=getLottoNumber")
    fun getLottoDataApi(
        @Query("drwNo") round: String
    ): Call<Result>
}