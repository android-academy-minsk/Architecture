package com.mvisample.presentation

import com.mvisample.domain.models.Location

interface Action

sealed class LocationAction : Action {

    object LoadAction : LocationAction()
    object AddLocationAction : LocationAction()
    data class RemoveLocationAction(val location: Location) : LocationAction()
    object ClearLocationsAction : LocationAction()
}
