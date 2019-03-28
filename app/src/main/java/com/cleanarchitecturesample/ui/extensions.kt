package com.cleanarchitecturesample.ui

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domain.featurelocation.entities.Location
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

fun Location.toPresentationModel(): Location = Location(latitude, longitude, date)

fun Date.toPrettifiedString(): String =
        SimpleDateFormat.getDateTimeInstance().run { format(this@toPrettifiedString) }

fun Double.toPrettifiedString(): String {

    val df = DecimalFormat("#.###")
    df.roundingMode = RoundingMode.CEILING

    return df.format(this)

}