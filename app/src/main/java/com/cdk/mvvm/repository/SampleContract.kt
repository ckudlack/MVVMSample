package com.cdk.mvvm.repository

import io.reactivex.Single

interface SampleContract {

    interface Repository {
        fun getFromServer(param: Int): Single<List<String>>
    }
}