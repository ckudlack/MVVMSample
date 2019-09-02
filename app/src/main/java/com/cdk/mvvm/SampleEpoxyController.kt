package com.cdk.mvvm

import com.airbnb.epoxy.TypedEpoxyController

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