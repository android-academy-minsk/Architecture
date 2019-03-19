package com.mvvmsample.framework

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {

    val main: CoroutineDispatcher

    val io: CoroutineDispatcher
}