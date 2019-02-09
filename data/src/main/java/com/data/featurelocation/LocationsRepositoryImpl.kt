package com.data.featurelocation

import com.data.datasource.DeviceLocationSource
import com.data.datasource.LocationPersistenceSource
import com.domain.featurelocation.models.Location
import com.domain.featurelocation.repository.LocationsRepository

class LocationsRepositoryImpl(private val locationPersistenceSource: LocationPersistenceSource,
                              private val deviceLocationSource: DeviceLocationSource): LocationsRepository {

    override fun getSavedLocations(): List<Location> = locationPersistenceSource.getPersistedLocations()

    override fun requestNewLocation(): List<Location> {
        val newLocation = deviceLocationSource.getDeviceLocation()
        locationPersistenceSource.saveNewLocation(newLocation)
        return getSavedLocations()
    }

}
