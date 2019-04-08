package com.mvisample.presentation.models

import com.mvisample.domain.models.Location
import java.text.SimpleDateFormat
import java.util.*


fun Location.toPresentationModel(): LocationModel =
    LocationModel(
        "$latitude, $longitude",
        date.toPrettifiedString()
    )

fun LocationModel.toDomainModel(): Location {
    val split = coordinates.split(delimiters = *arrayOf(", "), limit = 2)
    return Location(
        split[0].toDouble(),
        split[1].toDouble(),
        SimpleDateFormat.getDateTimeInstance().run { parse(date) }
    )
}

private fun Date.toPrettifiedString(): String =
    SimpleDateFormat.getDateTimeInstance().run { format(this@toPrettifiedString) }
