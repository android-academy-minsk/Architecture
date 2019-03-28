package com.data.datasource

import com.domain.featurelocation.entities.Location

interface LocationPersistenceSource {

    fun getPersistedLocations(): List<Location>

    fun saveNewLocation(location: Location)

    fun saveNewLocations(newLocations: List<Location>)

}