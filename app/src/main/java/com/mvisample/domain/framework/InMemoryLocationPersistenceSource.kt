package com.mvisample.domain.framework

import com.mvisample.domain.datasource.LocationPersistenceSource
import com.mvisample.domain.models.Location

class InMemoryLocationPersistenceSource : LocationPersistenceSource {

    private var locations = mutableListOf<Location>()

    override fun getPersistedLocations(): List<Location> = locations

    override fun saveNewLocation(location: Location) {
        locations.plusAssign(location)
    }

    override fun clearLocations() {
        locations.clear()
    }

    override fun removeLocation(location: Location) {
        locations.remove(location)
    }
}
