package com.data.featurelocation

import com.data.datasource.DeviceLocationSource
import com.data.datasource.LocationPersistenceSource
import com.domain.featurelocation.entities.Location
import com.domain.featurelocation.repository.LocationsRepository

class LocationsRepositoryImpl(private val locationPersistenceSource: LocationPersistenceSource,
                              private val deviceLocationSource: DeviceLocationSource): LocationsRepository {

    override fun getSavedLocations(): List<Location> = locationPersistenceSource.getPersistedLocations()

    override fun getNewLocations(): List<Location> = deviceLocationSource.getDeviceLocations()
            .run {
                locationPersistenceSource.saveNewLocations(this)
                getSavedLocations()
            }

    override fun requestNewLocation(): List<Location> {
        return locationPersistenceSource.saveNewLocation(deviceLocationSource.getDeviceLocation())
                .run { getSavedLocations() }
    }

}
