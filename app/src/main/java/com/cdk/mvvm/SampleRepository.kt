package com.cdk.mvvm

import io.reactivex.Single

class SampleRepository(private val service: ApiService) : SampleContract.Repository {

    override fun getFromServer(param: Int): Single<String> = service.getSomething(param)
}