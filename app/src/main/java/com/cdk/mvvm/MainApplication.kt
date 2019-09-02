package com.cdk.mvvm

import android.app.Application
import com.cdk.mvvm.di.appModules
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }
}