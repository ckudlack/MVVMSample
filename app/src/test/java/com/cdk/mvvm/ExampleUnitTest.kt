package com.cdk.mvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cdk.mvvm.repository.SampleContract
import com.cdk.mvvm.viewmodel.SampleViewModel
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Answers

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ExampleUnitTest {

    val mockRepo = mock<SampleContract.Repository>()

    val stubReponse = mock<Any>(defaultAnswer = Answers.RETURNS_DEEP_STUBS)

    val loadingObserver: Observer<Boolean> = mock()

    val responseObserver: Observer<Any> = mock()

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var viewModel: SampleViewModel

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        // setup stubs, mocks
        whenever(mockRepo.getFromServer(eq(1))).thenReturn(Single.just("string"))

        // setup VM
        viewModel = SampleViewModel(mockRepo)
    }

    @Test
    fun sampleTest() {
        viewModel.loading.observeForever(loadingObserver)
        viewModel.data.observeForever(responseObserver)

        assertEquals(false, viewModel.loading.value)
    }
}
