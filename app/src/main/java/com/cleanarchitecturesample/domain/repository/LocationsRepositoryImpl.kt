package com.cleanarchitecturesample.domain.repository

import com.cleanarchitecturesample.domain.datasource.DeviceLocationSource
import com.cleanarchitecturesample.domain.datasource.LocationPersistenceSource
import com.cleanarchitecturesample.domain.models.Location

class LocationsRepositoryImpl(private val locationPersistenceSource: LocationPersistenceSource,
                              private val deviceLocationSource: DeviceLocationSource): LocationsRepository {

    override fun getSavedLocations(): List<Location> = locationPersistenceSource.getPersistedLocations()

    override fun requestNewLocation(): List<Location> {
        val newLocation = deviceLocationSource.getDeviceLocation()
        locationPersistenceSource.saveNewLocation(newLocation)
        return getSavedLocations()
    }

}
