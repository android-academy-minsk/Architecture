package com.cleanarchitecturesample.framework

import com.data.datasource.LocationPersistenceSource
import com.domain.featurelocation.models.Location

class InMemoryLocationPersistenceSource : LocationPersistenceSource {

    private var locations: List<Location> = emptyList()

    override fun getPersistedLocations(): List<Location> = locations

    override fun saveNewLocation(location: Location) {
        locations = locations + location
    }
}