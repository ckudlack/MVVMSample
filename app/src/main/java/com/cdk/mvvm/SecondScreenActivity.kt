package com.cdk.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SecondScreenActivity : AppCompatActivity() {

    private val viewModelWithParam: SampleWithParamViewModel by viewModel {
        parametersOf(
            intent.getIntExtra(
                "PARAM",
                -1
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        val param = viewModelWithParam.param
        val toDouble = param.toDouble()
    }
}
