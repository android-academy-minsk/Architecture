package com.data.datasource

import com.domain.featurelocation.entities.Location

interface DeviceLocationSource {

    fun getDeviceLocations(): List<Location>

    fun getDeviceLocation(): Location

}