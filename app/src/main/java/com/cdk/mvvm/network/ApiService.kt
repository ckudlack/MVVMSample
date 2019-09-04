package com.cdk.mvvm.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    /**
     * also @Query("q") for query params
     */
    @GET("some_param_{p}")
    fun getSomething(@Path("p") p: Int): Single<List<String>>
}