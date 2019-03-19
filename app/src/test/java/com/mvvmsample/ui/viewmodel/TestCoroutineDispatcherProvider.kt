package com.mvvmsample.ui.viewmodel

import com.mvvmsample.framework.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

class TestCoroutineDispatcherProvider : CoroutineDispatcherProvider {

    override val main: CoroutineDispatcher = TestCoroutineDispatcher()

    override val io: CoroutineDispatcher = TestCoroutineDispatcher()
}

private class TestCoroutineDispatcher : CoroutineDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        block.run()
    }
}