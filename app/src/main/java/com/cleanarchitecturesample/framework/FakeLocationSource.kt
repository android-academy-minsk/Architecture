package com.cleanarchitecturesample.framework

import com.data.datasource.DeviceLocationSource
import com.domain.featurelocation.models.Location
import java.util.*

class FakeLocationSource : DeviceLocationSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location =
            Location(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date())
}