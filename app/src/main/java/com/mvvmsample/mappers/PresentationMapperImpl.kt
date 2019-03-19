package com.mvvmsample.mappers

import com.mvvmsample.model.LocationModel
import com.mvvmsample.location.Location
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date

class PresentationMapperImpl : PresentationMapper<Location, LocationModel> {

    override fun transform(entity: Location): LocationModel {
        return LocationModel(entity.latitude.toPrettifiedString(), entity.date.toPrettifiedString())
    }

}

private fun Date.toPrettifiedString(): String = SimpleDateFormat.getDateTimeInstance().format(this)

private fun Double.toPrettifiedString(): String {
    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.CEILING

    return df.format(this)
}
