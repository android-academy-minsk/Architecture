package com.mvisample.presentation

import com.mvisample.presentation.models.LocationModel

interface ViewState

data class LocationViewState(
    val loading: Boolean = false,
    val locations: List<LocationModel> = emptyList(),
    val error: Throwable? = null
) : ViewState {

    companion object {

        fun idle() = LocationViewState()
    }
}
