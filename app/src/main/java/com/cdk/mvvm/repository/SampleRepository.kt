package com.cdk.mvvm.repository

import com.cdk.mvvm.network.ApiService
import io.reactivex.Single

class SampleRepository(private val service: ApiService) :
    SampleContract.Repository {

    override fun getFromServer(param: Int): Single<String> = service.getSomething(param)
}