package com.mvisample.domain.datasource

import com.mvisample.domain.models.Location

interface LocationPersistenceSource {

    fun getPersistedLocations(): List<Location>

    fun saveNewLocation(location: Location)

    fun clearLocations()

    fun removeLocation(location: Location)
}
