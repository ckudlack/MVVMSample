package com.cdk.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cdk.mvvm.Resource
import com.cdk.mvvm.repository.SampleContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class SampleViewModel(private val repository: SampleContract.Repository) : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    /**
     * Can also create a Result wrapper class to handle the loading, success, and error states
     * as well as the data itself
     */
    val data: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }
    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val error: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    /**
     * Can use this as an alternative to the above approach
     * Pros are that it contains the data and state all in one class, you won't forget to update loading state
     * Con is that you're decision point for what to do based on state is now in the activity/fragment
     */
    val resource: MutableLiveData<Resource<List<String>>> by lazy { MutableLiveData<Resource<List<String>>>() }

    init {
        // if you put this call in onCreate of activity instead of here,
        // the call will be made again on rotation
        callServer()
    }

    private fun callServer() {
        loading.value = true

        disposable.add(
            repository.getFromServer(1)
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

    private fun callServerWithResource() {
        disposable.add(
            repository.getFromServer(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { resource.value = Resource.loading() } // check this works as intended
                .subscribeBy(
                    onSuccess = {
                        resource.value = Resource.success(it)
                    },
                    onError = {
                        resource.value = Resource.error(it.localizedMessage)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}