package com.mvisample.domain.framework

import android.os.SystemClock
import com.mvisample.domain.datasource.DeviceLocationSource
import com.mvisample.domain.models.Location
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

class FakeLocationSource : DeviceLocationSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location {
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.CEILING

        val lat = random.nextDouble() * 180 - 90
        val lon = random.nextDouble() * 360 - 180
        val time = (SystemClock.currentThreadTimeMillis() / 1000) * 1000
        return Location(df.format(lat).toDouble(), df.format(lon).toDouble(), Date(time))
    }
}
