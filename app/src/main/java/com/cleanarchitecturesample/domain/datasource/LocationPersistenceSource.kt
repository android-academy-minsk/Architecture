package com.cleanarchitecturesample.domain.datasource

import com.cleanarchitecturesample.domain.models.Location

interface LocationPersistenceSource {

    fun getPersistedLocations(): List<Location>

    fun saveNewLocation(location: Location)

}