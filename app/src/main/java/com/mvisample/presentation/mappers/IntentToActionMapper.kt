package com.mvisample.presentation.mappers

import com.mvisample.presentation.LocationAction
import com.mvisample.presentation.LocationAction.*
import com.mvisample.presentation.LocationIntent
import com.mvisample.presentation.models.toDomainModel

class IntentToActionMapper {

    fun map(intent: LocationIntent): LocationAction {
        return when (intent) {
            LocationIntent.InitialIntent -> LoadAction
            LocationIntent.AddLocationIntent -> AddLocationAction
            is LocationIntent.RemoveLocationIntent -> RemoveLocationAction(intent.location.toDomainModel())
            LocationIntent.ClearLocationsIntent -> ClearLocationsAction
        }
    }
}
