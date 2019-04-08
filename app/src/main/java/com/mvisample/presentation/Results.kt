package com.mvisample.presentation

import com.mvisample.domain.models.Location

interface Result

sealed class LocationResult : Result {

    sealed class LoadLocationsResult : LocationResult() {

        data class Success(val locations: List<Location>) : LoadLocationsResult()
        data class Failure(val error: Throwable) : LoadLocationsResult()
        object InProgress : LoadLocationsResult()
    }

    sealed class AddLocationResult : LocationResult() {

        data class Success(val locations: List<Location>) : AddLocationResult()
        data class Failure(val error: Throwable) : AddLocationResult()
        object InProgress : AddLocationResult()
    }

    sealed class RemoveLocationResult : LocationResult() {

        data class Success(val locations: List<Location>) : RemoveLocationResult()
        data class Failure(val error: Throwable) : RemoveLocationResult()
        object InProgress : RemoveLocationResult()
    }

    sealed class ClearLocationsResult : LocationResult() {

        object Success : ClearLocationsResult()
        data class Failure(val error: Throwable) : ClearLocationsResult()
        object InProgress : ClearLocationsResult()
    }
}
