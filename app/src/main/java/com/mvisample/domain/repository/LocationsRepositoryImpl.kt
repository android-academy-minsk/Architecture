package com.mvisample.domain.repository

import com.mvisample.domain.datasource.DeviceLocationSource
import com.mvisample.domain.datasource.LocationPersistenceSource
import com.mvisample.domain.models.Location

class LocationsRepositoryImpl(private val locationPersistenceSource: LocationPersistenceSource,
                              private val deviceLocationSource: DeviceLocationSource): LocationsRepository {

    override fun getSavedLocations(): List<Location> = locationPersistenceSource.getPersistedLocations()

    override fun requestNewLocation(): List<Location> {
        val newLocation = deviceLocationSource.getDeviceLocation()
        locationPersistenceSource.saveNewLocation(newLocation)
        return getSavedLocations()
    }

    override fun clearLocations() {
        locationPersistenceSource.clearLocations()
    }

    override fun removeLocation(location: Location) {
        locationPersistenceSource.removeLocation(location)
    }
}
