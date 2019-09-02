package com.cdk.mvvm

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * More info: https://android.jlelse.eu/koin-simple-android-di-a47827a707ce
 */

// In its own module since it can be used across modules
val apiModule = module {
    single {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://BASE_URL_ENDING_IN_/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

// In its own module since it can be used across modules
val repositoryModule = module {
    single<SampleContract.Repository> { SampleRepository(get()) }
}

val sampleModule = module {
    viewModel { SampleViewModel(get()) }

    // viewModel with an included param (passed in thru an intent, for example)
    viewModel { (id: Int) -> SampleWithParamViewModel(id, get()) }
}

val appModules = listOf(apiModule, sampleModule, repositoryModule)