package com.cdk.mvvm

import androidx.annotation.DimenRes


data class Padding(
    @DimenRes val topResId: Int,
    @DimenRes val bottomResId: Int,
    @DimenRes val leftResId: Int,
    @DimenRes val rightResId: Int
) {
    constructor(@DimenRes resId: Int) : this(resId, resId, resId, resId)
}