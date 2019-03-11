package com.cleanarchitecturesample.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProvider(val main: CoroutineDispatcher = Dispatchers.Main,
                                  val io: CoroutineDispatcher = Dispatchers.IO)