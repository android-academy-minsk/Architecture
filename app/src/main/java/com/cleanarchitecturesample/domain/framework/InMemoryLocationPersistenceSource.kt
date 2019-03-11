package com.cleanarchitecturesample.domain.framework

import com.cleanarchitecturesample.domain.datasource.LocationPersistenceSource
import com.cleanarchitecturesample.domain.models.Location

class InMemoryLocationPersistenceSource : LocationPersistenceSource {

    private var locations: List<Location> = emptyList()

    override fun getPersistedLocations(): List<Location> = locations

    override fun saveNewLocation(location: Location) {
        locations += location
    }
}