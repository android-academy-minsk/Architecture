package com.data.datasource

import com.domain.featurelocation.models.Location

interface DeviceLocationSource {

    fun getDeviceLocation(): Location

}