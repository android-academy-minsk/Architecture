package com.mvisample.domain.datasource

import com.mvisample.domain.models.Location

interface DeviceLocationSource {

    fun getDeviceLocation(): Location
}
