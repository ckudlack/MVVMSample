package com.cdk.mvvm.epoxy

import com.airbnb.epoxy.TypedEpoxyController

class SampleEpoxyController : TypedEpoxyController<List<String>>() {

    override fun buildModels(data: List<String>) {
        data.forEachIndexed { index, s ->
            commonTextView {
                id("string_$index")
                body(s)
            }
        }
    }
}