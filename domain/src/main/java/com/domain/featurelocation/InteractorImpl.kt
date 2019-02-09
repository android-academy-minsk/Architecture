package com.domain.featurelocation

import com.domain.featurelocation.models.Location
import com.domain.featurelocation.usecases.GetLocationsUseCase
import com.domain.featurelocation.usecases.RequestNewLocationUseCase

class InteractorImpl(private val getLocations: GetLocationsUseCase,
                     private val requestNewLocation: RequestNewLocationUseCase): Interator {

    override fun getLocations(): List<Location> = getLocations.invoke()

    override fun requestNewLocation(): List<Location> = requestNewLocation.invoke()

}