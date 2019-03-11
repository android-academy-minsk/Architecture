package com.cleanarchitecturesample.domain.datasource

import com.cleanarchitecturesample.domain.models.Location

interface DeviceLocationSource {

    fun getDeviceLocation(): Location

}