package com.domain.featurelocation.usecases

import com.domain.featurelocation.models.Location
import com.domain.featurelocation.repository.LocationsRepository

class GetLocationsUseCase(private val locationsRepository: LocationsRepository) {

    operator fun invoke(): List<Location> = locationsRepository.getSavedLocations()

}
