package com.mvvmsample.framework

import com.mvvmsample.location.DeviceLocationSource
import com.mvvmsample.location.Location
import java.util.*

class FakeLocationSource : DeviceLocationSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location {
        // Delay makes it look more natural
        Thread.sleep(1000)
        return Location(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date())
    }
}