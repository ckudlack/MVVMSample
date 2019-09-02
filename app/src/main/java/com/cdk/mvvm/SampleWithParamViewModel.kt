package com.cdk.mvvm

import androidx.lifecycle.ViewModel

/**
 * A factory VM generated with a paramter (ie an ID)
 */
class SampleWithParamViewModel(
    val param: Int,
    private val repository: SampleContract.Repository
) : ViewModel() {


}