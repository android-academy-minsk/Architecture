package com.mvvmsample.framework

import com.mvvmsample.location.LocationPersistenceSource
import com.mvvmsample.location.Location

class InMemoryLocationPersistenceSource : LocationPersistenceSource {

    private var locations: List<Location> = emptyList()

    override fun getPersistedLocations(): List<Location> = locations

    override fun saveNewLocation(location: Location) {
        locations = locations + location
    }
}