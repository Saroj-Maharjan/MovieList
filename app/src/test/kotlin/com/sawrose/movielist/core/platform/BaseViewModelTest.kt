package com.sawrose.movielist.core.platform

import android.arch.lifecycle.MutableLiveData
import com.sawrose.movielist.AndroidTest
import com.sawrose.movielist.core.exception.Failure
import com.sawrose.movielist.core.exception.Failure.NetworkConnection
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class BaseViewModelTest : AndroidTest() {

    @Test
    fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()
        viewModel.handleError(NetworkConnection())

        val failure = viewModel.failure
        val error = viewModel.failure.value

        failure shouldBeInstanceOf MutableLiveData::class.java
        error shouldBeInstanceOf NetworkConnection::class.java
    }

    private class MyViewModel : BaseViewModel() {

        fun handleError(failure: Failure) = handleFailure(failure)
    }
}