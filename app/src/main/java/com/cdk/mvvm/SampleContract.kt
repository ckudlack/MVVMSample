package com.cdk.mvvm

import io.reactivex.Single

interface SampleContract {

    interface Repository {
        fun getFromServer(param: Int): Single<String>
    }
}