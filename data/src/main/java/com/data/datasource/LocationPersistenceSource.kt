package com.data.datasource

import com.domain.featurelocation.models.Location

interface LocationPersistenceSource {

    fun getPersistedLocations(): List<Location>

    fun saveNewLocation(location: Location)

}