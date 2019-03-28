package com.cleanarchitecturesample.framework

import com.data.datasource.LocationPersistenceSource
import com.domain.featurelocation.entities.Location

/**
*
* Not synchronized storage
*
* */
class InMemoryLocationPersistenceSource : LocationPersistenceSource {

    private var locations: List<Location> = emptyList()

    override fun getPersistedLocations(): List<Location> = locations

    override fun saveNewLocation(location: Location) {
        locations = locations + location
    }

    override fun saveNewLocations(newLocations: List<Location>) {
        locations = locations + newLocations
    }
}