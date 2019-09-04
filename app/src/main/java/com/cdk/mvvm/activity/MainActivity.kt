package com.cdk.mvvm.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cdk.mvvm.R
import com.cdk.mvvm.viewmodel.SampleViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * If using MvRx don't forget to change this to extend #BaseMvRxActivity() !!!
 */

class MainActivity : AppCompatActivity() {

    private val viewModel: SampleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Create VM instance without Koin extension functions
         */
        /*
           val vm = ViewModelProviders.of(this).get(SampleViewModel::class.java)
         */

        next_button.setOnClickListener {
            startActivity(Intent(this, SecondScreenActivity::class.java).apply {
                putExtra(
                    "PARAM",
                    4
                )
            })
        }

        viewModel.data.observe(this, Observer<List<String>> { data ->
            // UI updates here
        })
    }
}
