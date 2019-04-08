package com.mvisample.domain

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface SchedulersProvider {

    fun background(): Scheduler

    fun ui(): Scheduler

    class Impl : SchedulersProvider {

        override fun background(): Scheduler {
            return Schedulers.computation()
        }

        override fun ui(): Scheduler {
            return AndroidSchedulers.mainThread()
        }
    }
}
