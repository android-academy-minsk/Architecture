package com.mvisample.domain.repository


import com.mvisample.domain.models.Location

interface LocationsRepository {

    fun getSavedLocations(): List<Location>

    fun requestNewLocation(): List<Location>

    fun clearLocations()

    fun removeLocation(location: Location)
}
