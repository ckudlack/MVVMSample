package com.cdk.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cdk.mvvm.repository.SampleContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * A factory VM generated with a parameter (ie an ID)
 */
class SampleWithParamViewModel(
    param: Int,
    private val repository: SampleContract.Repository
) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    /**
     * Can also create a Result wrapper class to handle the loading, success, and error states
     * as well as the data itself
     */
    val data: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val error: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        // if you put this call in onCreate of activity instead of here,
        // the call will be made again on rotation
        callServer(param)
    }

    private fun callServer(param: Int) {
        loading.value = true

        disposable.add(
            repository.getFromServer(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        data.value = it
                        loading.value = false
                    },
                    onError = {
                        error.value = it.localizedMessage
                        loading.value = false
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}