package com.cleanarchitecturesample.ui

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

class TestCoroutineDispatcher : CoroutineDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        block.run()
    }
}