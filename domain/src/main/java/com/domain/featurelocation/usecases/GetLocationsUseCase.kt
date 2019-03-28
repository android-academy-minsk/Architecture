package com.domain.featurelocation.usecases

import com.domain.featurelocation.entities.Location
import com.domain.featurelocation.repository.LocationsRepository

class GetLocationsUseCase(private val locationsRepository: LocationsRepository) {

    fun invokeSavedLocations(): List<Location> = locationsRepository.getSavedLocations()

    fun invokeNewLocations(): List<Location> = locationsRepository.getNewLocations()
            .filter { data -> data.latitude != -1.0 }

}
