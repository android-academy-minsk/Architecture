package com.mvvmsample.location

import androidx.annotation.WorkerThread

interface DeviceLocationSource {

    @WorkerThread
    fun getDeviceLocation(): Location

}