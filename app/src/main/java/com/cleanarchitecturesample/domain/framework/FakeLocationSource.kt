package com.cleanarchitecturesample.domain.framework

import com.cleanarchitecturesample.domain.datasource.DeviceLocationSource
import com.cleanarchitecturesample.domain.models.Location
import java.util.*

class FakeLocationSource : DeviceLocationSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location =
            Location(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date())
}