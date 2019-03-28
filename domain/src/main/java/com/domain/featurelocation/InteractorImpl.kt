package com.domain.featurelocation

import com.domain.featurelocation.entities.Location
import com.domain.featurelocation.usecases.GetLocationsUseCase
import com.domain.featurelocation.usecases.RequestNewLocationUseCase

class InteractorImpl(private val getLocations: GetLocationsUseCase,
                     private val requestNewLocation: RequestNewLocationUseCase): Interator {

    override fun getLocations(): List<Location> = getLocations.invokeSavedLocations()

    override fun getNewLocations(): List<Location> = getLocations.invokeNewLocations()

    override fun requestNewLocation(): List<Location> = requestNewLocation.invoke()

}