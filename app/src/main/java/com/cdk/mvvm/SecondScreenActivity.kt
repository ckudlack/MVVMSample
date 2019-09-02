package com.cdk.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_second_screen.*
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

    private val controller = SampleEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        // attach RV to adapter (in this case, inside the controller)
        with(recycler_view) {
            layoutManager =
                LinearLayoutManager(this@SecondScreenActivity, RecyclerView.VERTICAL, false)
            setController(controller)
        }

        // update on observe
        viewModelWithParam.data.observe(this, Observer { data ->
            controller.setData(data)
        })
    }
}
