package com.cdk.mvvm.mvrx

import com.airbnb.mvrx.*
import com.cdk.mvvm.repository.SampleContract
import org.koin.android.ext.android.inject


data class SampleState(
    val title: String = "",
    val items: List<String> = emptyList(),
    val sampleRequest: Async<List<String>> = Uninitialized
) : MvRxState {

    /**
     * Add this if we are passing parameters into the fragment with MvRx.KEY_ARG as the key
     */
    /*
        @Suppress("unused")
    constructor(titleParam: String) : this(
        title = titleParam
    )
     */
}


class SampleMvRxViewModel(
    sampleState: SampleState,
    private val repository: SampleContract.Repository
): MvRxViewModel<SampleState>(sampleState) {

    fun getItems(param: Int) {
        withState { state ->
            if (state.sampleRequest is Loading) return@withState

            repository.getFromServer(param)
                .execute {
                    copy(
                        sampleRequest = it,
                        items = it() ?: emptyList()
                    )
                }
        }
    }


    companion object : MvRxViewModelFactory<SampleMvRxViewModel, SampleState> {
        // @JvmStatic
        override fun create(viewModelContext: ViewModelContext, state: SampleState): SampleMvRxViewModel? {
            val repository: SampleContract.Repository by viewModelContext.activity.inject()
            return SampleMvRxViewModel(state, repository)
        }
    }
}