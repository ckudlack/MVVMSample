package com.cdk.mvvm.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.cdk.mvvm.commonTextView

class SampleEpoxyController : TypedEpoxyController<String>() {

    override fun buildModels(data: String) {

        // let's pretend we're processing a list
        val list = listOf(data)

        list.forEachIndexed { index, s ->
            commonTextView {
                id("string_$index")
                body(s)
            }
        }
    }
}