package com.cleanarchitecturesample.framework

import com.data.datasource.DeviceLocationSource
import com.domain.featurelocation.entities.Location
import java.util.*

class FakeLocationSource : DeviceLocationSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location =
            Location(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date())

    override fun getDeviceLocations(): List<Location> {
        val data = arrayListOf<Location>()
        return data.apply {
            for (i in 0..5) {
                add(getDeviceLocation())
            }
        }
    }
}