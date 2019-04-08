package com.mvisample.presentation

import com.mvisample.presentation.models.LocationModel

interface Intent

sealed class LocationIntent : Intent {

    object InitialIntent : LocationIntent()

    object AddLocationIntent : LocationIntent()

    data class RemoveLocationIntent(val location: LocationModel) : LocationIntent()

    object ClearLocationsIntent : LocationIntent()
}
